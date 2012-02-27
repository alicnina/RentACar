<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:ui="http://java.sun.com/jsf/facelets"
	xmlns:f="http://java.sun.com/jsf/core"
	xmlns:h="http://java.sun.com/jsf/html"
	xmlns:rich="http://richfaces.org/rich"
	xmlns:a4j="http://richfaces.org/a4j"
	xmlns:p="http://primefaces.prime.com.tr/ui">
<h:head>
	<title>Mapa</title>
	<link rel="stylesheet" type="text/css" href="style.css"></link>
	<script src="http://maps.google.com/maps/api/js?sensor=false" type="text/javascript" ></script>
</h:head>
<body>
	<h:form>
		<h:panelGrid columns="2" width="100%" border="0">
			<h:outputText value="Geografska �irina:"></h:outputText>
			<h:inputText value="#{mapBean.pozicijaLat}">
				<f:validateDoubleRange minimum="-90.0" maximum="90.0"/>
				<f:ajax event="blur" execute="@this" render="poruke"></f:ajax>
			</h:inputText>
			<h:outputText value="Geografska du�ina:"></h:outputText>
			<h:inputText value="#{mapBean.pozicijaLong}">
				<f:validateDoubleRange minimum="-90.0" maximum="90.0"/>
				<f:ajax event="blur" execute="@this" render="poruke"></f:ajax>
			</h:inputText>
			<h:outputText value=""></h:outputText>
			<h:messages styleClass="upozorenje" id="poruke"></h:messages>
			<h:outputText value=""></h:outputText>
			<a4j:commandButton value="Postavi mapu na datu lokaciju" action="#{mapBean.setMap}" render="mapa"></a4j:commandButton>
			<h:outputText value=""></h:outputText>
			<a4j:commandButton value="Postavi marker na trenutnu lokaciju" action="#{mapBean.dodajMarker}" render="mapa"></a4j:commandButton>
		</h:panelGrid>
		<p:gmap center="#{mapBean.pozicijaString}" id="mapa" zoom="15" type="TERRAIN" 
		style="width:600px;height:400px" model="#{mapBean.model}"
		mapTypeControl="false" navigationControl="false" />
	</h:form>
	
</body>
</html>