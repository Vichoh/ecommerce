
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Nombre</th>
                <th>Eliminar</th>
            </tr>
	</thead>
        <tbody>
            <c:forEach var="i" items="${listadoCategorias}">
                <tr>
                    <td>${i.catId}</td>
                    <td>${i.catNombre}</td>
                    <td ><a href="editarCategoria?id=${i.catId}" > Editar</a> <a href="eliminarCategoria?cat_id=${i.catId}" >Eliminar</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
