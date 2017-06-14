<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form action="editarProducto" method="post">
    <fieldset>
        <legend>Editar Producto</legend>
        <input type="hidden" name="id_pro" value="${id_pro}">
        Nombre:
        <input type="text" name="nombre_edi_producto" value=""><br><br>
        Descripcion:
        <input type="text" name="descripcion_edi_producto" value=""><br><br>
        Precio:
        <input type="text" name="precio_edi_producto" value=""><br><br>
        Actualizacion:
        <input type="text" name="ultima_actializacion_edi_producto" value=""><br><br>
        Categoria:
        
         
        
        <select name="edi_categoria">
            <c:forEach var="i" items="${listadoCategorias}">
                <option value="${i.catId}" name="categoria_producto">${i.catNombre}</option>
            </c:forEach>
            
        </select><br>
        <input type="reset"  value="cancelar">
        <input type="submit"  value="Crear"> 
    
    
</form>