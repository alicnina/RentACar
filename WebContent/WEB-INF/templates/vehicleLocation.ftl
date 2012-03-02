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
		<p:gmap center="${latLonPositionString}" id="mapa" zoom="15" type="TERRAIN" 
		style="width:600px;height:400px" model="#{mapModel}"
		mapTypeControl="false" navigationControl="false" />
	</h:form>
	
</body>
</html>