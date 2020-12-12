<?php 
	$conexion = mysqli_connect("localhost", "root", "", "escuela") or die( mysqli_error() );
	$respuesta = array();

	if ($_SERVER['REQUEST_METHOD'] == 'POST') {
		$cadena_json = file_get_contents('php://input'); //Recibe información por HTTP
		$datos = json_decode($cadena_json, true);

		$n = $datos['n'];

		$sql = "SELECT * FROM alumnos WHERE nombre LIKE '" . $n . "%'";
		$consulta = mysqli_query($conexion, $sql);

		if (mysqli_num_rows($consulta)>0) {
			$respuesta['alumnos'] = array();
			while ($fila = mysqli_fetch_assoc($consulta)) {
				
				$alumno = array();

				$alumno['nc'] = $fila['num_control'];
				$alumno['n'] = $fila['nombre'];
				$alumno['pa'] = $fila['primer_ap'];
				$alumno['sa'] = $fila['segundo_apo'];
				$alumno['e'] = $fila['edad'];
				$alumno['s'] = $fila['semestre'];
				$alumno['c'] = $fila['carrera'];

				array_push($respuesta['alumnos'], $alumno);
			}

		$respuesta['exito'] = 1;
		echo json_encode($respuesta);

		} else {
			$respuesta['exito'] = 0;
			$respuesta['msj'] = 0;
			echo json_encode($respuesta);
		}
	} 
 ?>