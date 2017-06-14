
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Nombre</th>
                <th>Descripcion</th>
                <th>Precio</th>
                <th>Ultima Actualizacion</th>
                <th>Categoria</th>
                <th></th>
                
                
            </tr>
	</thead>
        <tbody>
            <c:forEach var="i" items="${listadoProductos}">
                <tr>
                    <td>${i.proId}</td>
                    <td>${i.proNombre}</td>
                    <td>${i.proDescripcion}</td>
                    <td>${i.proPrecio}</td>
                    <td>${i.proUltimaActualizacion}</td>
                    <td>${i.cat.catNombre}</td>
                    <td ><a href="formEditarProducto?id=${i.proId}" > Editar</a> <a href="eliminarProducto?pro_id=${i.proId}" >Eliminar</a></td>
                    
                   
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
