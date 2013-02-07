package com.jonnyzzz.teamcity.plugins.node.agent.processes

import java.io.File
import jetbrains.buildServer.agent.BuildProcess
import jetbrains.buildServer.agent.BuildProcessFacade
import jetbrains.buildServer.agent.BuildRunnerContext
import jetbrains.buildServer.runner.SimpleRunnerConstants

public open class CommandlineBuildProcessFactoryImpl(val facade: BuildProcessFacade): CommandlineBuildProcessFactory {

  public override fun executeCommandLine(hostContext: BuildRunnerContext,
                                         program: String,
                                         argz: Collection<String>,
                                         workingDir: File,
                                         additionalEnvironment: Map<String, String>): BuildProcess {

    val context = facade.createBuildRunnerContext(
            hostContext.getBuild(),
            SimpleRunnerConstants.TYPE,
            workingDir.getPath(),
            hostContext)

    additionalEnvironment.entrySet().forEach { context.addEnvironmentVariable(it.key, it.value) }

    val newArgz = arrayListOf<String>()

    newArgz add program
    newArgz addAll argz

    val commandLine = joinCommandLineArguments(newArgz)
    hostContext.getBuild().getBuildLogger().message("Node command: " + commandLine)
    context.addRunnerParameter(SimpleRunnerConstants.USE_CUSTOM_SCRIPT, "true")
    context.addRunnerParameter(SimpleRunnerConstants.SCRIPT_CONTENT, commandLine)
    return facade.createExecutable(hostContext.getBuild(), context)
  }
  private open fun joinCommandLineArguments(cmd: Collection<String>): String {
    val cmdbuf = StringBuilder(80)
    var isFirst = true
    for (aCmd : String in cmd)
    {
      if (!isFirst)
        cmdbuf.append(' ')
      else
        isFirst = false

      if ((aCmd.indexOf(' ')) < 0 && (aCmd.indexOf('\t')) < 0)
      {
        cmdbuf.append(aCmd)
        continue
      }

      if (aCmd.charAt(0) != '"')
      {
        cmdbuf.append('"')
        cmdbuf.append(aCmd)
        if (aCmd.endsWith("\\"))
          cmdbuf.append("\\")
        cmdbuf.append('"')
      } else {
        if (aCmd.endsWith("\""))
          cmdbuf.append(aCmd)
        else
          throw IllegalArgumentException()
      }
    }
    return cmdbuf.toString()
  }
}