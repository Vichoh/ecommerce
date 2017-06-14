<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form action="guardarOrden" method="post">
    <fieldset>
        <legend>Nueva Orden</legend>
        Fecha creacion:
        <input type="text" name="fecha_creacion" value=""><br><br>
        Numero Confirmacion
        <input type="text" name="numero_confirmacion" value=""><br><br>
        Precio Total:
        <input type="text" name="precio_total" value=""><br><br>
        
        Cliente
        
        
        <select name="clientes">
            <c:forEach var="i" items="${clientes}">
               
                <option value="${i.cliId}" name="categoria_producto">${i.cliNombres}</option>
            </c:forEach>
            
        </select><br>
        
        
          
        <input type="reset"  value="cancelar">
        <input type="submit"  value="Crear"> 
    
    
</form>
        