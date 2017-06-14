<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form action="actualizarOrden" method="post">
    <fieldset>
        <legend>Editar Orden</legend>
        <input type="hidden" name="id_ord" value="${ordId}">
        Fecha creacion:
        <input type="text" name="fecha_ed_creacion" value=""><br><br>
        Numero Confirmacion
        <input type="text" name="numero_ed_confirmacion" value=""><br><br>
        Precio Total:
        <input type="text" name="precio_ed_total" value=""><br><br>
        
        Cliente
        
        
        <select name="ed_clientes">
            <c:forEach var="i" items="${clientes}">
               
                <option value="${i.cliId}" name="categoria_producto">${i.cliNombres}</option>
            </c:forEach>
            
        </select><br>
        
        
          
        <input type="reset"  value="cancelar">
        <input type="submit"  value="Editar"> 
    
    
</form>
        