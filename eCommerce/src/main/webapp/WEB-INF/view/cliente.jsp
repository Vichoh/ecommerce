

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Nombre</th>
                <th>Apellido</th>
                <th>Telefono</th>
                <th>Direccion</th>
                <th>Comuna</th>
            </tr>
	</thead>
        <tbody>
            <c:forEach var="i" items="${listaClientes}">
                <tr>
                    <td>${i.cliId}</td>
                    <td>${i.cliNombres}</td>
                    <td>${i.cliApellidos}</td>
                    <td>${i.cliTelefono}</td>
                    <td>${i.cliDireccion}</td> 
                    <td>${i.cliComuna}</td>                     
                       
                    <td ><a href="goFormEditar?id=${i.cliId}" > Editar</a> <a href="eliminarCliente?cli_id=${i.cliId}" >Eliminar</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
