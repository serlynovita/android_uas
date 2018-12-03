<?php
	
	//use Restserver\Libraries\REST_Controller;
	defined('BASEPATH') OR exit('No direct script access allowed');

	require APPPATH . '/libraries/REST_Controller.php';
	require APPPATH . '/libraries/Format.php';

	class OngkosKirim extends REST_Controller {

		function ongkoskirim_get(){
			$get_ongkos_kirim = $this->db->query("SELECT id_ongkir, kota, harga FROM ongkos_kirim")->result();
			$this->response(array("status" => "success", "result" => $get_ongkos_kirim));
		}

		function ongkoskirim_post(){

			$action = $this->post('action');
			$data_ongkos_kirim = array(
							'id_ongkir' => $this->post('id_ongkir'),
							'kota' => $this->post('kota'),
							'harga' => $this->post('harga')
						);

			switch ($action) {
				case 'insert':
					$this->insertPembeli($data_ongkos_kirim);
					break;
				case 'update':
					$this->updatePembeli($data_ongkos_kirim);
					break;
				case 'delete':
					$this->deletePembeli($data_ongkos_kirim);
					break;
				default:
					$this->response(
							array(
								"status" => "failed",
								"message" => "action harus diisi"
							)
					);
					break;
			}
		}

		function insertOngkosKirim($data_ongkos_kirim){
			if(empty($data_ongkos_kirim['kota']) || empty($data_ongkos_kirim['harga'])){
				$this->response(
							array(
								"status" => "failed",
								"message" => "Kota / harga harus diisi"
							)
				);
			}
			else {
				//$data_ongkos_kirim['photo_url'] = $this->uploadPhoto();
				$do_insert = $this->db->insert('ongkos_kirim',$data_ongkos_kirim);

				if($do_insert){
					$this->response(
								array(
									"status" => "success",
									"result" => array($data_ongkos_kirim),
									"message" => $do_insert
								)
					);
				}
			}
		}

		function updateOngkosKirim($data_ongkos_kirim){
			if(empty($data_ongkos_kirim['kota']) || empty($data_ongkos_kirim['harga'])){
				$this->response(
							array(
								"status" => "failed",
								"message" => "Nama Pembeli / harga / telepon / email / password harus diisi"
							)
				);
			}
			else {
				$get_ongkos_kirim_baseID = $this->db->query("
						SELECT 1 FROM ongkos_kirim WHERE id_ongkos_kirim = {$data_ongkos_kirim['id_ongkos_kirim']}
					")->num_rows();

				if($get_ongkos_kirim_baseID === 0){
					$this->response(
							array(
								"status" => "failed",
								"message" => "ID Ongkos Kirim tidak ditemukan"
							)
					);
				}
				else {
					$update = $this->db->query("
							UPDATE ongkos_kirim SET
							kota = '{$data_ongkos_kirim['kota']}',
							harga = '{$data_ongkos_kirim['harga']}'
							WHERE id_ongkos_kirim = {$data_ongkos_kirim['id_ongkos_kirim']}
						");
					
					if($update){
						$this->response(
							array(
								"status" => "success",
								"result" => array($data_ongkos_kirim),
								"message" => $update
							)
						);
					}
				}
			}
		}

		function deletePembeli($data_ongkos_kirim){
			if(empty($data_ongkos_kirim['id_ongkos_kirim'])){
				$this->response(
							array(
								"status" => "failed",
								"message" => "ID Ongkos Kirim harus diisi"
							)
				);
			}
			else {
				$get_ongkos_kirim_baseID = $this->db->query("
						SELECT 1 FROM ongkos_kirim WHERE id_ongkos_kirim = {$data_ongkos_kirim['id_ongkos_kirim']}")->num_rows();

				if($get_ongkos_kirim_baseID > 0){
					$this->db->query("
						DELETE FROM ongkos_kirim WHERE id_ongkos_kirim = {$data_ongkos_kirim['id_ongkos_kirim']}
					");
				$this->response(
					array(
						"status" => "success",
						"message" => "Data ID = " .$data_ongkos_kirim['id_ongkos_kirim']. " berhasil dihapus"
						)
					);
				}
				else {
					$this->response(
						array(
							"status" => "failed",
							"message" => "ID Ongkos Kirim tidak ditemukan"
						)
					);
				}
			}
		}
	}
?>