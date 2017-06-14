<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form action="guardarProducto" method="post">
    <fieldset>
        <legend>Nuevo Producto</legend>
        Nombre:
        <input type="text" name="nombre_producto" value=""><br><br>
        Descripcion:
        <input type="text" name="descripcion_producto" value=""><br><br>
        Precio:
        <input type="text" name="precio_producto" value=""><br><br>
        Actualizacion:
        <input type="text" name="ultima_actializacion_producto" value=""><br><br>
        Categoria:
        
         
        
        <select name="categoria">
            <c:forEach var="i" items="${listadoCategorias}">
                <option value="${i.catId}" name="categoria_producto">${i.catNombre}</option>
            </c:forEach>
            
        </select><br>
        <input type="reset"  value="cancelar">
        <input type="submit"  value="Crear"> 
    
    
</form>