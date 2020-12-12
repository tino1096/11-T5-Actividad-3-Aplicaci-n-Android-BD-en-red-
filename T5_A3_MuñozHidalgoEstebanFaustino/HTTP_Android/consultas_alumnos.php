<?php 
    include("../scripts/servidor/conexion_PDO.php");
    $conexionPDO = conexionPDO("root", "", "escuela");
            
    $consulta = "SELECT * FROM alumnos";

    $sentencia = $conexionPDO->prepare($consulta);
    $sentencia->execute([]);

    $respuesta['alumnos'] = array();
    while ($fila = $sentencia->fetch(PDO::FETCH_ASSOC)) { 
        $alumno = array();

        $alumno['nc'] = $fila['num_control'];
        $alumno['n'] = $fila['nombre'];
        $alumno['pa'] = $fila['primer_ap'];
        $alumno['sa'] = $fila['segundo_ap'];
        $alumno['e'] = $fila['edad'];
        $alumno['s'] = $fila['semestre'];
        $alumno['c'] = $fila['carrera'];

        array_push($respuesta['alumnos'], $alumno);
        $respuesta['exito'] = 1;
		
    }

    $respuesta['exito'] = 1;
    echo json_encode($respuesta);

	/*if (mysqli_num_rows($consulta)>0) {

	} else {
		$respuesta['exito'] = 0;
		$respuesta['msj'] = 0;
		echo json_encode($respuesta);
	}*/
 ?>