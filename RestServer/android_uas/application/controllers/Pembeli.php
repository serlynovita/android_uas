<?php
	
	//use Restserver\Libraries\REST_Controller;
	defined('BASEPATH') OR exit('No direct script access allowed');

	require APPPATH . '/libraries/REST_Controller.php';
	require APPPATH . '/libraries/Format.php';

	class Pembeli extends REST_Controller {

		function pembeli_get(){
			$get_pembeli = $this->db->query("SELECT id_pembeli, nama_pembeli, alamat, telpn, email, password FROM pembeli")->result();
			$this->response(array("status" => "success", "result" => $get_pembeli));
		}

		function ambilId_get(){
			$get_ambilIdPembeli = $this->db->query("SELECT MAX(id_pembeli) FROM `pembeli`")->result();
			$this->response(array("status" => "success", "result" => $get_ambilIdPembeli));
		}

		function pembeli_post(){

			$action = $this->post('action');
			$data_pembeli = array(
							'id_pembeli' => $this->post('id_pembeli'),
							'nama_pembeli' => $this->post('nama_pembeli'),
							'alamat' => $this->post('alamat'),
							'telpn' => $this->post('telpn'),
							'email' => $this->post('email'),
							'password' => $this->post('password')
						);

			switch ($action) {
				case 'insert':
					$this->insertPembeli($data_pembeli);
					break;
				case 'update':
					$this->updatePembeli($data_pembeli);
					break;
				case 'delete':
					$this->deletePembeli($data_pembeli);
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

		function insertPembeli($data_pembeli){
			if(empty($data_pembeli['nama_pembeli']) || empty($data_pembeli['alamat']) || empty($data_pembeli['telpn']) || empty($data_pembeli['email']) || empty($data_pembeli['password'])){
				$this->response(
							array(
								"status" => "failed",
								"message" => "Nama Pembeli / alamat / telepon / email / password harus diisi"
							)
				);
			}
			else {
				//$data_pembeli['photo_url'] = $this->uploadPhoto();
				$do_insert = $this->db->insert('pembeli',$data_pembeli);

				if($do_insert){
					$this->response(
								array(
									"status" => "success",
									"result" => array($data_pembeli),
									"message" => $do_insert
								)
					);
				}
			}
		}

		function updatePembeli($data_pembeli){
			if(empty($data_pembeli['nama_pembeli']) || empty($data_pembeli['alamat']) || empty($data_pembeli['telpn']) || empty($data_pembeli['email']) || empty($data_pembeli['password'])){
				$this->response(
							array(
								"status" => "failed",
								"message" => "Nama Pembeli / alamat / telepon / email / password harus diisi"
							)
				);
			}
			else {
				$get_pembeli_baseID = $this->db->query("
						SELECT 1 FROM pembeli WHERE id_pembeli = {$data_pembeli['id_pembeli']}
					")->num_rows();

				if($get_pembeli_baseID === 0){
					$this->response(
							array(
								"status" => "failed",
								"message" => "ID Pembeli tidak ditemukan"
							)
					);
				}
				else {
					$update = $this->db->query("
							UPDATE pembeli SET
							nama_pembeli = '{$data_pembeli['nama_pembeli']}',
							alamat = '{$data_pembeli['alamat']}',
							telpn = '{$data_pembeli['telpn']}',
							email = '{$data_pembeli['email']}',
							password = '{$data_pembeli['password']}'
							WHERE id_pembeli = {$data_pembeli['id_pembeli']}
						");
					

					if($update){
						$this->response(
							array(
								"status" => "success",
								"result" => array($data_pembeli),
								"message" => $update
							)
						);
					}
				}
			}
		}

		function deletePembeli($data_pembeli){
			if(empty($data_pembeli['id_pembeli'])){
				$this->response(
							array(
								"status" => "failed",
								"message" => "ID Pembeli harus diisi"
							)
				);
			}
			else {
				$get_pembeli_baseID = $this->db->query("
						SELECT 1 FROM pembeli WHERE id_pembeli = {$data_pembeli['id_pembeli']}")->num_rows();

				if($get_pembeli_baseID > 0){
					$this->db->query("
						DELETE FROM pembeli WHERE id_pembeli = {$data_pembeli['id_pembeli']}
					");
				$this->response(
					array(
						"status" => "success",
						"message" => "Data ID = " .$data_pembeli['id_pembeli']. " berhasil dihapus"
						)
					);
				}
				else {
					$this->response(
						array(
							"status" => "failed",
							"message" => "ID Pembeli tidak ditemukan"
						)
					);
				}
			}
		}
	}
?>