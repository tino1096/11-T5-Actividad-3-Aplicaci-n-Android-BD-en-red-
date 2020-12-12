<?php 
	class Conexion {
		$conexion;
		function _construct() {
			$this-->conectar();
		}

		function _destruct() {
			$this-->desconectar();
		}

		function conectar() {
			require_once(_DIR_ . 'configuracion_BD.php');
			$this-->conexion = mysqli_connect(host, user, password, db) or die( mysqli_error() );
			return this-->conexion;
		}
	}
 ?>