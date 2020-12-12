<?php 
	include("../scripts/servidor/conexion_PDO.php");
    $conexionPDO = conexionPDO("root", "", "escuela");

	$respuesta = array();

	if ($_SERVER['REQUEST_METHOD'] == 'POST') {
		$cadena_json = file_get_contents('php://input'); //Recibe información por HTTP
		$datos = json_decode($cadena_json, true);

		$nc = $datos['nc'];
        
        $consulta = "DELETE FROM alumnos WHERE num_control = ?";
        $sentencia = $conexionPDO->prepare($consulta);
        $sentencia->execute([$nc]);

		if ($sentencia) {
			$respuesta['exito'] = 1;
			$respuesta['msj'] = "Eliminacion correcta";
			echo json_encode($respuesta);
		} else {
			$respuesta['exito'] = 0;
			$respuesta['msj'] = "Error en la eliminacion";
			echo json_encode($respuesta);
		}
	}
 ?>