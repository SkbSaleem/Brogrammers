<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html">
<%@ page import="edu.unsw.comp9321.CreateGraph"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>
<!--Based on the implementation found at https://github.com/Rathachai/d3rdf/blob/master/index.html -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Graph</title>
<%@page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="graphStyles.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script src="http://d3js.org/d3.v3.min.js"></script>
<script>
	function filterNodesById(nodes, id) {
		return nodes.filter(function(n) {
			return n.id === id;
		});
	}

	function triplesToGraph(triples) {
		svg.html("");
		//Graph
		var graph = {
			nodes : [],
			links : []
		};
		//Initial Graph from triples
		triples.forEach(function(triple) {
			var subjId = triple.subject;
			var predId = triple.predicate;
			var objId = triple.object;

			var subjNode = filterNodesById(graph.nodes, subjId)[0];
			var objNode = filterNodesById(graph.nodes, objId)[0];

			if (subjNode == null) {
				subjNode = {
					id : subjId,
					label : subjId,
					weight : 1
				};
				graph.nodes.push(subjNode);
			}

			if (objNode == null) {
				objNode = {
					id : objId,
					label : objId,
					weight : 1
				};
				graph.nodes.push(objNode);
			}

			graph.links.push({
				source : subjNode,
				target : objNode,
				predicate : predId,
				weight : 1
			});
		});

		return graph;
	}

	function update() {
		// ==================== Add Marker ====================
		svg.append("svg:defs").selectAll("marker").data([ "end" ]).enter()
				.append("svg:marker").attr("id", String).attr("viewBox",
						"0 -5 10 10").attr("refX", 30).attr("refY", -0.5).attr(
						"markerWidth", 6).attr("markerHeight", 6).attr(
						"orient", "auto").append("svg:polyline").attr("points",
						"0,-5 10,0 0,5");

		// ==================== Add Links ====================
		var links = svg.selectAll(".link").data(graph.links).enter().append(
				"line").attr("marker-end", "url(#end)").attr("class", "link")
				.attr("stroke-width", 1);//links

		// ==================== Add Link Names =====================
		var linkTexts = svg.selectAll(".link-text").data(graph.links).enter()
				.append("text").attr("class", "link-text").text(function(d) {
					return d.predicate;
				});
		//linkTexts.append("title")
		//		.text(function(d) { return d.predicate; });

		// ==================== Add Link Names =====================
		var nodeTexts = svg.selectAll(".node-text").data(graph.nodes).enter()
				.append("text").attr("class", "node-text").text(function(d) {
					return d.label;
				});
		//nodeTexts.append("title")
		//		.text(function(d) { return d.label; });

		// ==================== Add Node =====================
		var nodes = svg.selectAll(".node").data(graph.nodes).enter().append(
				"circle").attr("class", "node").attr("r", 8).call(force.drag);//nodes

		// ==================== Force ====================
		force.on("tick", function() {
			nodes.attr("cx", function(d) {
				return d.x;
			}).attr("cy", function(d) {
				return d.y;
			});

			links.attr("x1", function(d) {
				return d.source.x;
			}).attr("y1", function(d) {
				return d.source.y;
			}).attr("x2", function(d) {
				return d.target.x;
			}).attr("y2", function(d) {
				return d.target.y;
			});

			nodeTexts.attr("x", function(d) {
				return d.x + 12;
			}).attr("y", function(d) {
				return d.y + 3;
			});

			linkTexts.attr("x", function(d) {
				return 4 + (d.source.x + d.target.x) / 2;
			}).attr("y", function(d) {
				return 4 + (d.source.y + d.target.y) / 2;
			});
		});

		// ==================== Run ====================
		force.nodes(graph.nodes).links(graph.links).charge(-500).linkDistance(
				100).start();
	}
</script>
</head>

<body>
<h1>Graph Visualization</h1>
	<div id="svg-body" class="panel-body">
	<form method="post" action="graphTables.jsp">
	<input type="submit" value="View Table">
	
	<script>
	<% 
	CreateGraph graph = new CreateGraph();
	List<Map<String,String>> triplesmap = graph.getTriplesMap();
	request.getSession().setAttribute("list", triplesmap);%>
		var triples = new Array();
		<c:forEach var="trip" items="<%=triplesmap%>">
		var triple = {};
		triple["subject"] = "${trip['subject']}";
		triple["predicate"] = "${trip['predicate']}";
		triple["object"] = "${trip['object']}";
		triples.push(triple);
		</c:forEach>
		var svg = d3.select("#svg-body").append("svg").attr("width", 800).attr(
				"height", 600);
		var force = d3.layout.force().size([ 800, 600 ]).linkDistance(500);
		var graph = triplesToGraph(triples);

		update();
	</script>
</form>
	</div>
</body>
</html>