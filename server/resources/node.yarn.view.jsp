<%--
  ~ Copyright 2013-2017 Eugene Petrenko
  ~
  ~ Licensed under the Apache License, Version 2.0 (the "License");
  ~ you may not use this file except in compliance with the License.
  ~ You may obtain a copy of the License at
  ~
  ~ http://www.apache.org/licenses/LICENSE-2.0
  ~
  ~ Unless required by applicable law or agreed to in writing, software
  ~ distributed under the License is distributed on an "AS IS" BASIS,
  ~ WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  ~ See the License for the specific language governing permissions and
  ~ limitations under the License.
  --%>

<%@ include file="/include.jsp"%>
<%@ taglib prefix="forms" tagdir="/WEB-INF/tags/forms" %>
<%@ taglib prefix="props" tagdir="/WEB-INF/tags/props" %>
<%@ taglib prefix="l" tagdir="/WEB-INF/tags/layout" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="bean" class="com.jonnyzzz.teamcity.plugins.node.common.YarnBean"/>
<jsp:useBean id="propertiesBean" scope="request" type="jetbrains.buildServer.controllers.BasePropertiesBean"/>

<div class="parameter">
  <!-- TODO: introduce controller to present targets in more readable way -->
  Run targets: <props:displayValue name="${bean.yarnCommandsKey}" showInPopup="${true}" emptyValue="${bean.yarnCommandsDefault}"/>
</div>

<div class="parameter">
  Yarn: <props:displayValue name="${bean.toolPathKey}" emptyValue="<default>"/>
</div>

<props:viewWorkingDirectory/>

<div class="parameter">
  Additional command line arguments: <props:displayValue name="${bean.commandLineParameterKey}" showInPopup="${true}" emptyValue="<empty>"/>
</div>
