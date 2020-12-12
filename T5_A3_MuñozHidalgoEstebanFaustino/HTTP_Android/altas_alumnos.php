<?php 
	include("../scripts/servidor/conexion_PDO.php");
    $conexionPDO = conexionPDO("root", "", "escuela");

	$respuesta = array();

	if ($_SERVER['REQUEST_METHOD'] == 'POST') {
		$cadena_json = file_get_contents('php://input'); //Recibe información por HTTP
		$datos = json_decode($cadena_json, true);

		$nc = $datos['nc'];
		$n = $datos['n'];
		$pa = $datos['pa'];
		$sa = $datos['sa'];
		$e = $datos['e'];
		$s = $datos['s'];
		$c = $datos['c'];

		$consulta = "INSERT INTO alumnos VALUES (?, ?, ?, ?, ?, ?, ?)";
        $sentencia = $conexionPDO->prepare($consulta);
        $sentencia->execute([$nc, $n, $pa, $sa, $e, $s, $c]);

		if ($sentencia) {
			$respuesta['exito'] = 1;
			$respuesta['msj'] = "Insercion correcta";
			echo json_encode($respuesta);
		} else {
			$respuesta['exito'] = 0;
			$respuesta['msj'] = "Error en la insercion";
			echo json_encode($respuesta);
		}
	}
 ?>