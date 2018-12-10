<?php
    
    //use Restserver\Libraries\REST_Controller;
    defined('BASEPATH') OR exit('No direct script access allowed');

    require APPPATH . '/libraries/REST_Controller.php';
    require APPPATH . '/libraries/Format.php';

    class Transaksi extends REST_Controller {

        function transaksi_get(){
            $get_transaksi = $this->db->query("SELECT t.id_transaksi, t.id_pembeli, t.id_ongkir, t.total_harga, t.tgl_beli, t.status FROM pembeli , transaksi t, ongkos_kirim WHERE t.id_pembeli=pembeli.id_pembeli AND t.id_ongkir=ongkos_kirim.id_ongkir")->result();
            $this->response(array("status" => "success", "result" => $get_transaksi));
        }

        function transaksi_post(){

            $action = $this->post('action');
            $data_transaksi = array(
                            'id_transaksi' => $this->post('id_transaksi'),
                            'id_pembeli' => $this->post('id_pembeli'),
                            'id_ongkir' => $this->post('id_ongkir'),
                            'total_harga' => $this->post('total_harga'),
                            'tgl_beli' => $this->post('tgl_beli'),
                            'status' => $this->post('status')
                        );

            switch ($action) {
                case 'insert':
                    $this->insertTransaksi($data_transaksi);
                    break;
                case 'update':
                    $this->updateTransaksi($data_transaksi);
                    break;
                case 'delete':
                    $this->deleteTransaksi($data_transaksi);
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

        function insertTransaksi($data_transaksi){
            if(empty($data_transaksi['id_pembeli']) || empty($data_transaksi['id_ongkir']) || empty($data_transaksi['total_harga']) || empty($data_transaksi['tgl_beli']) || empty($data_transaksi['status'])){
                $this->response(
                            array(
                                "status" => "failed",
                                "message" => "ID Pembeli / ID Ongkos Kirim / Total Harga / Tanggal Beli / Status harus diisi"
                            )
                );
            }
            else {

                $get_transaksi_pembeli_baseID = $this->db->query("
                        SELECT 1 FROM pembeli WHERE id_pembeli = {$data_transaksi['id_pembeli']}
                    ")->num_rows();

                 $get_transaksi_ongkos_kirim_baseID = $this->db->query("
                        SELECT 1 FROM ongkos_kirim WHERE id_ongkir = {$data_transaksi['id_ongkir']}
                    ")->num_rows();

                if($get_transaksi_pembeli_baseID === 0){
                    $this->response(
                            array(
                                "status" => "failed",
                                "message" => "ID Pembeli tidak ditemukan"
                            )
                    );
                }
                else if($get_transaksi_ongkos_kirim_baseID === 0){
                    $this->response(
                            array(
                                "status" => "failed",
                                "message" => "ID Ongkos Kirim tidak ditemukan"
                            )
                    );
                }
                else {
                    $do_insert = $this->db->insert('transaksi',$data_transaksi);
                    if($do_insert){
                        //$data_transaksi['photo_url'] = $this->uploadPhoto();

                        $this->response(
                                    array(
                                        "status" => "success",
                                        "result" => array($data_transaksi),
                                        "message" => $do_insert
                                    )
                        );
                    }
                }
            }
        }

        function updateTransaksi($data_transaksi){
             if(empty($data_transaksi['id_pembeli']) || empty($data_transaksi['id_ongkir']) || empty($data_transaksi['total_harga']) || empty($data_transaksi['tgl_beli']) || empty($data_transaksi['status'])){
                $this->response(
                            array(
                                "status" => "failed",
                                "message" => "ID Pembeli / ID Ongkos Kirim / Total Harga / Tanggal Beli / Status harus diisi"
                            )
                );
            }
            else {
                $get_transaksi_baseID = $this->db->query("
                        SELECT 1 FROM transaksi WHERE id_transaksi = {$data_transaksi['id_transaksi']}
                    ")->num_rows();

                $get_transaksi_pembeli_baseID = $this->db->query("
                        SELECT 1 FROM pembeli WHERE id_pembeli = {$data_transaksi['id_pembeli']}
                    ")->num_rows();

                $get_transaksi_ongkos_kirim_baseID = $this->db->query("
                        SELECT 1 FROM ongkos_kirim WHERE id_ongkir = {$data_transaksi['id_ongkir']}
                    ")->num_rows();

                if($get_transaksi_baseID === 0){
                    $this->response(
                            array(
                                "status" => "failed",
                                "message" => "ID Transaksi tidak ditemukan"
                            )
                    );
                }
                else if($get_transaksi_pembeli_baseID === 0){
                    $this->response(
                            array(
                                "status" => "failed",
                                "message" => "ID Pembeli tidak ditemukan"
                            )
                    );
                }
                else if($get_transaksi_ongkos_kirim_baseID === 0){
                    $this->response(
                            array(
                                "status" => "failed",
                                "message" => "ID Ongkos Kirim tidak ditemukan"
                            )
                    );
                }
                else {
                    $update = $this->db->query("
                            UPDATE transaksi SET
                            id_pembeli = '{$data_transaksi['id_pembeli']}',
                            id_ongkir = '{$data_transaksi['id_ongkir']}',
                            total_harga = '{$data_transaksi['total_harga']}',
                            tgl_beli = '{$data_transaksi['tgl_beli']}',
                            status = '{$data_transaksi['status']}'
                            WHERE id_transaksi = {$data_transaksi['id_transaksi']}
                        ");
                    

                    if($update){
                        $this->response(
                            array(
                                "status" => "success",
                                "result" => array($data_transaksi),
                                "message" => $update
                            )
                        );
                    }
                }
            }
        }

        function deleteTransaksi($data_transaksi){
            if(empty($data_transaksi['id_transaksi'])){
                $this->response(
                            array(
                                "status" => "failed",
                                "message" => "ID Transaksi harus diisi"
                            )
                );
            }
            else {
                $get_transaksi_baseID = $this->db->query("
                        SELECT 1 FROM transaksi WHERE id_transaksi = {$data_transaksi['id_transaksi']}")->num_rows();

                if($get_transaksi_baseID > 0){
                    $this->db->query("
                        DELETE FROM transaksi WHERE id_transaksi = {$data_transaksi['id_transaksi']}
                    ");
                    $this->response(
                    array(
                        "status" => "success",
                        "message" => "Data ID = " .$data_transaksi['id_transaksi']. " berhasil dihapus"
                        )
                    );
                }
                else {
                    $this->response(
                        array(
                            "status" => "failed",
                            "message" => "ID Transaksi tidak ditemukan"
                        )
                    );
                }
            }
        }
    }
?>