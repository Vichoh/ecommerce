<form action="actualizarCliente" method="post">
    <fieldset>
        <legend>Actualizar Cliente</legend>
      
        <input type="hidden" name="id_cat" value="${id_cliente}">
        Nombres :
        <input type="text" name="nombres_act_cliente" value="">
        <br><br>
        Apellidos:
        <input type="text" name="apellidos_act_cliente" value=""><br><br>
        Telefono:
        <input type="text" name="telefono_act_cliente" value=""><br><br>
        Direccion:
        <input type="text" name="direccion_act_cliente" value=""><br><br>
        Comuna:
        <input type="text" name="comuna_act_cliente" value=""><br><br>
            
        < <input type="submit"  value="Guardar"> 
    
    
</form>
