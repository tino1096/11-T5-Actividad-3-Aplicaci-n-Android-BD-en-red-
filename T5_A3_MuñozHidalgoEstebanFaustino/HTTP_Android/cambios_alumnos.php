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
        
        $consulta = "UPDATE alumnos SET nombre = ?, primer_ap =  ?, segundo_ap = ?, edad = ?, semestre = ?, carrera = ? WHERE num_control = ?";
        $sentencia = $conexionPDO->prepare($consulta);
        $sentencia->execute([$n, $pa, $sa, $e, $s, $c, $nc]);

		if ($sentencia) {
			$respuesta['exito'] = 1;
			$respuesta['msj'] = "Modificacion correcta";
			echo json_encode($respuesta);
		} else {
			$respuesta['exito'] = 0;
			$respuesta['msj'] = "Error en la modificacion";
			echo json_encode($respuesta);
		}
	}
 ?>