<?php 
    include("../scripts/servidor/conexion_PDO.php");
    $conexionPDO = conexionPDO("root", "", "bd_usuarios_escuela");
            
    if ($_SERVER['REQUEST_METHOD'] == 'POST') {
		$cadena_json = file_get_contents('php://input'); //Recibe información por HTTP
		$datos = json_decode($cadena_json, true);

		$user = $datos['user'];
		$password = $datos['password'];

        $consulta = "SELECT * FROM usuarios WHERE user = ? AND password = SHA(?);";

        $sentencia = $conexionPDO->prepare($consulta);
        $sentencia->execute([$user, $password]);

        $respuesta['usuario'] = array();
        
        $fila = $sentencia->fetch(PDO::FETCH_ASSOC); 

        $usuario = array();

        $usuario['user'] = $fila['user'];
        $usuario['password'] = $password;

        array_push($respuesta['usuario'], $usuario);
        $respuesta['exito'] = 1;


        $respuesta['exito'] = 1;
        echo json_encode($respuesta);
    }
 ?>