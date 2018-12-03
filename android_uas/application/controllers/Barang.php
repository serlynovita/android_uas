<?php
	
	//use Restserver\Libraries\REST_Controller;
	defined('BASEPATH') OR exit('No direct script access allowed');

	require APPPATH . '/libraries/REST_Controller.php';
	require APPPATH . '/libraries/Format.php';

	class Barang extends REST_Controller {

		private $folder_upload = 'uploads/';

		function barang_get(){
			$get_barang = $this->db->query("SELECT id_barang, nama_barang, warna_barang, kategori_barang, berat_barang, foto , deskripsi, harga, stok FROM barang")->result();
			$this->response(array("status" => "success", "result" => $get_barang));
		}

		function barang_post(){

			$action = $this->post('action');
			$data_barang = array(
							'id_barang' => $this->post('id_barang'),
							'nama_barang' => $this->post('nama_barang'),
							'warna_barang' => $this->post('warna_barang'),
							'kategori_barang' => $this->post('kategori_barang'),
							'berat_barang' => $this->post('berat_barang'),
							'foto' => $this->post('foto'),
							'deskripsi' => $this->post('deskripsi'),
							'harga' => $this->post('harga'),
							'stok' => $this->post('stok')
						);

			switch ($action) {
				case 'insert':
					$this->insertPembeli($data_barang);
					break;
				case 'update':
					$this->updatePembeli($data_barang);
					break;
				case 'delete':
					$this->deletePembeli($data_barang);
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

		function insertBarang($data_barang){
			if(empty($data_barang['nama_barang']) || empty($data_barang['warna_barang']) || empty($data_barang['kategori_barang']) || empty($data_barang['berat_barang']) || empty($data_barang['deskripsi']) || empty($data_barang['harga']) || empty($data_barang['stok'])){
				$this->response(
							array(
								"status" => "failed",
								"message" => "Nama Barang / Warna Barang / Kategori Barang / Berat Barang / Deskripsi / Harga / Stok harus diisi"
							)
				);
			}
			else {
				$data_barang['foto'] = $this->uploadPhoto();
				$do_insert = $this->db->insert('barang',$data_barang);

				if($do_insert){
					$this->response(
								array(
									"status" => "success",
									"result" => array($data_barang),
									"message" => $do_insert
								)
					);
				}
			}
		}

		function updateBarang($data_barang){
			if(empty($data_barang['nama_barang']) || empty($data_barang['warna_barang']) || empty($data_barang['kategori_barang']) || empty($data_barang['berat_barang']) || empty($data_barang['deskripsi']) || empty($data_barang['harga']) || empty($data_barang['stok'])){
				$this->response(
							array(
								"status" => "failed",
								"message" => "Nama Barang / Warna Barang / Kategori Barang / Berat Barang / Deskripsi / Harga / Stok harus diisi"
							)
				);
			}
			else {
				$get_barang_baseID = $this->db->query("
						SELECT 1 FROM barang WHERE id_barang = {$data_barang['id_barang']}
					")->num_rows();

				if($get_barang_baseID === 0){
					$this->response(
							array(
								"status" => "failed",
								"message" => "ID Barang tidak ditemukan"
							)
					);
				}
				else {
					$data_barang['foto'] = $this->uploadPhoto();

					if($data_barang['foto']){
						$update = $this->db->query("
								UPDATE barang SET 
								nama_barang = '{$data_barang['nama_barang']}',
								warna_barang = '{$data_barang['warna_barang']}',
								kategori_barang = '{$data_barang['kategori_barang']}',
								berat_barang = '{$data_barang['berat_barang']}',
								deskripsi = '{$data_barang['deskripsi']}',
								harga = '{$data_barang['harga']}',
								stok = '{$data_barang['stok']}',
								foto = '{$data_barang['foto']}'
								WHERE id_barang = '{$data_barang['id_barang']}'
							");
					}
					else {
						$update = $this->db->query("
								UPDATE barang SET
								nama_barang = '{$data_barang['nama_barang']}',
								warna_barang = '{$data_barang['warna_barang']}',
								kategori_barang = '{$data_barang['kategori_barang']}',
								berat_barang = '{$data_barang['berat_barang']}',
								deskripsi = '{$data_barang['deskripsi']}',
								harga = '{$data_barang['harga']}',
								stok = '{$data_barang['stok']}',
								WHERE id_barang = {$data_barang['id_barang']}
							");
					}

					if($update){
						$this->response(
							array(
								"status" => "success",
								"result" => array($data_barang),
								"message" => $update
							)
						);
					}
				}
			}
		}

		function deletePembeli($data_barang){
			if(empty($data_barang['id_barang'])){
				$this->response(
							array(
								"status" => "failed",
								"message" => "ID Pembeli harus diisi"
							)
				);
			}
			else {
				$get_barang_baseID = $this->db->query("
						SELECT 1 FROM barang WHERE id_barang = {$data_barang['id_barang']}")->num_rows();

				if($get_barang_baseID > 0){
					$get_foto = $this->db->query("
							SELECT foto FROM barang WHERE id_barang = {$data_barang['id_barang']}
						")->result();

					if(!empty($get_foto)){
						$photo_nama_file = basename($get_foto[0]->foto);
						$photo_lokasi_file = realpath(FCPATH . $this->folder_upload . $photo_nama_file);

						if(file_exists($photo_lokasi_file)){
							unlink($photo_lokasi_file);
						} 
						$this->db->query("
								DELETE FROM barang WHERE id_barang = {$data_barang['id_barang']}
							");
						$this->response(
								array(
									"status" => "success",
									"message" => "Data ID = " .$data_barang['id_barang']. " berhasil dihapus"
								)
						);
					}
				}
				else {
					$this->response(
						array(
							"status" => "failed",
							"message" => "ID Barang tidak ditemukan"
						)
					);
				}
			}
		}

		function uploadPhoto(){
			if(isset($_FILES['foto']) && $_FILES['foto']['size'] > 0){
				$config['upload_path'] = realpath(FCPATH . $this->folder_upload);
				$config['allowed_types'] = 'jpg|png';

				$this->load->library('upload',$config);
				$this->load->helper('url');

				if($this->upload->do_upload('foto')){
					$img_data = $this->upload->data();
					$post_image = base_url(). $this->folder_upload.$img_data['file_name'];
				}
				else {
					$post_image = $this->upload->display_errors();
				}
			}
			else {
				$post_image = '';
			}
			return $post_image;
		}
	}
?>