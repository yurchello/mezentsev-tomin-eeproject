<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<script type="text/javascript" src="/static/js/jquery-1.12.3.js"></script>
<script type="text/javascript" src="/static/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="/static/js/dataTables.select.min.js"></script>
<script type="text/javascript" src="/static/js/dataTables.editor.min.js"></script>
<script type="text/javascript" src="/static/js/dataTables.buttons.min.js"></script>

<link href="<c:url value='/static/css/jquery.dataTables.min.css' />" rel="stylesheet"/>
<link href="<c:url value='/static/css/select.dataTables.min.css' />" rel="stylesheet"/>
<link href="<c:url value='/static/css/editor.dataTables.min.css' />" rel="stylesheet"/>
<link href="<c:url value='/static/css/buttons.dataTables.min.css' />" rel="stylesheet"/>

<head>
    <title>Login Home Page</title>
</head>
<script>
    //    var editor;
    //    $(document).ready(function() {
    //        editor = new $.fn.dataTable.Editor( {
    //            ajax: "../php/staff.php",
    //            table: "#example",
    //            fields: [ {
    //                label: "First name:",
    //                name: "first_name"
    //            }, {
    //                label: "Last name:",
    //                name: "last_name"
    //            }, {
    //                label: "Position:",
    //                name: "position"
    //            }, {
    //                label: "Office:",
    //                name: "office"
    //            }, {
    //                label: "Extension:",
    //                name: "extn"
    //            }, {
    //                label: "Start date:",
    //                name: "start_date",
    //                type: "datetime"
    //            }, {
    //                label: "Salary:",
    //                name: "salary"
    //            }
    //            ]
    //        } );
    //
    //        $('#example').DataTable( {
    //            dom: "Bfrtip",
    //            ajax: "../php/staff.php",
    //            columns: [
    //                { data: null, render: function ( data, type, row ) {
    //                    // Combine the first and last names into a single table field
    //                    return data.first_name+' '+data.last_name;
    //                } },
    //                { data: "position" },
    //                { data: "office" },
    //                { data: "extn" },
    //                { data: "start_date" },
    //                { data: "salary", render: $.fn.dataTable.render.number( ',', '.', 0, '$' ) }
    //            ],
    //            select: true,
    //            buttons: [
    //                { extend: "create", editor: editor },
    //                { extend: "edit",   editor: editor },
    //                { extend: "remove", editor: editor }
    //            ]
    //        } );
    //    } );
</script>
<head>

</head>
<body>
<div class="well lead">Home</div>
<div class="generic-container col-xs-12" style="height:50px;">


</div>


</body>
</html>



