
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Fecha Creacion</th>
                <th>Numero Confirmacion</th>
                <th>Precio total</th>
                <th>Cliente</th>
            </tr>
	</thead>
        <tbody>
            <c:forEach var="i" items="${listadoOrdens}">
                <tr>
                    <td>${i.ordId}</td>
                    <td>${i.ordFcreacion}</td>
                    <td>${i.ordNumConfirmacion}</td>
                    <td>${i.ordPrecioTotal}</td>
                    <td>${i.cli.cliNombres}</td>
                    <td ><a href="editarOrden?id=${i.ordId}" > Editar</a> <a href="eliminarOrden?ord_id=${i.ordId}" >Eliminar</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
