package com.alibaba.p3c.idea.listener

import com.alibaba.p3c.idea.compatible.inspection.Inspections
import com.alibaba.p3c.idea.config.P3cConfig
import com.alibaba.p3c.idea.i18n.P3cBundle
import com.alibaba.p3c.idea.service.FileListenerService
import com.alibaba.p3c.idea.util.HighlightInfoTypes
import com.alibaba.p3c.idea.util.HighlightSeverities
import com.alibaba.p3c.pmd.I18nResources
import com.alibaba.smartfox.idea.common.util.getService
import com.intellij.codeInsight.daemon.impl.SeverityRegistrar
import com.intellij.ide.util.RunOnceUtil
import com.intellij.openapi.actionSystem.ActionManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.project.ProjectManagerListener
import com.intellij.openapi.startup.StartupActivity

class MyProjectStartupActivity : StartupActivity, ProjectManagerListener {


    private val p3cConfig = P3cConfig::class.java.getService()

    override fun runActivity(project: Project) {
        // 使用RunOnceUtil注册相关内容
        RunOnceUtil.runOnceForApp("com.alibaba.p3c.idea.listener.registerStandard") {
            SeverityRegistrar.registerStandard(HighlightInfoTypes.BLOCKER, HighlightSeverities.BLOCKER)
            SeverityRegistrar.registerStandard(HighlightInfoTypes.CRITICAL, HighlightSeverities.CRITICAL)
            SeverityRegistrar.registerStandard(HighlightInfoTypes.MAJOR, HighlightSeverities.MAJOR)
        }

        I18nResources.changeLanguage(p3cConfig.locale)
        val analyticsGroup = ActionManager.getInstance().getAction(analyticsGroupId)
        analyticsGroup.templatePresentation.text = P3cBundle.getMessage(analyticsGroupText)
        Inspections.addCustomTag(project, "date")
        val fileService = project.getService(FileListenerService::class.java)
        fileService.projectOpened(project)
    }

    override fun projectClosed(project: Project) {
        val fileService = project.getService(FileListenerService::class.java)
        fileService.projectClosed()
    }

    companion object {
        const val analyticsGroupId = "com.alibaba.p3c.analytics.action_group"
        const val analyticsGroupText = "$analyticsGroupId.text"
    }

}
