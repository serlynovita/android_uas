<?php
    
    //use Restserver\Libraries\REST_Controller;
    defined('BASEPATH') OR exit('No direct script access allowed');

    require APPPATH . '/libraries/REST_Controller.php';
    require APPPATH . '/libraries/Format.php';

    class DetailTransaksi extends REST_Controller {

        function detailtransaksi_get(){
            $get_detailtransaksi = $this->db->query("SELECT dt.id_detail, dt.id_transaksi, dt.id_barang, dt.jumlah_beli FROM Transaksi , detail_transaksi dt, barang WHERE dt.id_transaksi=transaksi.id_transaksi AND dt.id_barang=barang.id_barang")->result();
            $this->response(array("status" => "success", "result" => $get_detailtransaksi));
        }

        function detailDetailtransaksi_post(){

            $action = $this->post('action');
            $data_detailtransaksi = array(
                            'id_detail' => $this->post('id_detail'),
                            'id_transaksi' => $this->post('id_transaksi'),
                            'id_barang' => $this->post('id_barang'),
                            'jumlah_beli' => $this->post('jumlah_beli')
                        );

            switch ($action) {
                case 'insert':
                    $this->insertTransaksi($data_detailtransaksi);
                    break;
                case 'update':
                    $this->updateTransaksi($data_detailtransaksi);
                    break;
                case 'delete':
                    $this->deleteTransaksi($data_detailtransaksi);
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

        function insertDetailTransaksi($data_detailtransaksi){
            if(empty($data_detailtransaksi['id_transaksi']) || empty($data_detailtransaksi['id_barang']) || empty($data_detailtransaksi['jumlah_beli'])){
                $this->response(
                            array(
                                "status" => "failed",
                                "message" => "ID Transaksi / ID Barang / Jumlah Beli  harus diisi"
                            )
                );
            }
            else {

                $get_detailtransaksi_transaksi_baseID = $this->db->query("
                        SELECT 1 FROM transaksi WHERE id_transaksi = {$data_detailtransaksi['id_transaksi']}
                    ")->num_rows();

                 $get_detailtransaksi_barang_baseID = $this->db->query("
                        SELECT 1 FROM barang WHERE id_barang = {$data_detailtransaksi['id_barang']}
                    ")->num_rows();

                if($get_detailtransaksi_transaksi_baseID === 0){
                    $this->response(
                            array(
                                "status" => "failed",
                                "message" => "ID Transaksi tidak ditemukan"
                            )
                    );
                }
                else if($get_detailtransaksi_barang_baseID === 0){
                    $this->response(
                            array(
                                "status" => "failed",
                                "message" => "ID Barang tidak ditemukan"
                            )
                    );
                }
                else {
                    if($do_insert){
                        //$data_detailtransaksi['photo_url'] = $this->uploadPhoto();
                        $do_insert = $this->db->insert('detail_transaksi',$data_detailtransaksi);

                        $this->response(
                                    array(
                                        "status" => "success",
                                        "result" => array($data_detailtransaksi),
                                        "message" => $do_insert
                                    )
                        );
                    }
                }
            }
        }

        function updateDetailTransaksi($data_detailtransaksi){
            if(empty($data_detailtransaksi['id_transaksi']) || empty($data_detailtransaksi['id_barang']) || empty($data_detailtransaksi['jumlah_beli'])){
                $this->response(
                            array(
                                "status" => "failed",
                                "message" => "ID Transaksi / ID Barang / Jumlah Beli  harus diisi"
                            )
                );
            }
            else {
                $get_detailtransaksi_baseID = $this->db->query("
                        SELECT 1 FROM detail_transaksi WHERE id_detail = {$data_detailtransaksi['id_detail']}
                    ")->num_rows();

                $get_detailtransaksi_transaksi_baseID = $this->db->query("
                        SELECT 1 FROM transaksi WHERE id_transaksi = {$data_detailtransaksi['id_transaksi']}
                    ")->num_rows();

                 $get_detailtransaksi_barang_baseID = $this->db->query("
                        SELECT 1 FROM barang WHERE id_barang = {$data_detailtransaksi['id_barang']}
                    ")->num_rows();

                if($get_detailtransaksi_baseID === 0){
                    $this->response(
                            array(
                                "status" => "failed",
                                "message" => "ID Detail Transaksi tidak ditemukan"
                            )
                    );
                }
                else if($get_detailtransaksi_transaksi_baseID === 0){
                    $this->response(
                            array(
                                "status" => "failed",
                                "message" => "ID Pembeli tidak ditemukan"
                            )
                    );
                }
                else if($get_detailtransaksi_barang_baseID === 0){
                    $this->response(
                            array(
                                "status" => "failed",
                                "message" => "ID Ongkos Kirim tidak ditemukan"
                            )
                    );
                }
                else {
                    $update = $this->db->query("
                            UPDATE detailtransaksi SET
                            id_transaksi = '{$data_detailtransaksi['id_transaksi']}',
                            id_barang = '{$data_detailtransaksi['id_barang']}',
                            jumlah_beli = '{$data_detailtransaksi['jumlah_beli']}',
                            tgl_beli = '{$data_detailtransaksi['tgl_beli']}',
                            status = '{$data_detailtransaksi['status']}'
                            WHERE id_detail = {$data_detailtransaksi['id_detail']}
                        ");
                    

                    if($update){
                        $this->response(
                            array(
                                "status" => "success",
                                "result" => array($data_detailtransaksi),
                                "message" => $update
                            )
                        );
                    }
                }
            }
        }

        function deleteTransaksi($data_detailtransaksi){
            if(empty($data_detailtransaksi['id_detail'])){
                $this->response(
                            array(
                                "status" => "failed",
                                "message" => "ID Detail Transaksi harus diisi"
                            )
                );
            }
            else {
                $get_detailtransaksi_baseID = $this->db->query("
                        SELECT 1 FROM detail_transaksi WHERE id_detail = {$data_detailtransaksi['id_detail']}")->num_rows();

                if($get_detailtransaksi_baseID > 0){
                    $this->db->query("
                        DELETE FROM detail_transaksi WHERE id_detail = {$data_detailtransaksi['id_detail']}
                    ");
                    $this->response(
                    array(
                        "status" => "success",
                        "message" => "Data ID = " .$data_detailtransaksi['id_detail']. " berhasil dihapus"
                        )
                    );
                }
                else {
                    $this->response(
                        array(
                            "status" => "failed",
                            "message" => "ID Detail Transaksi tidak ditemukan"
                        )
                    );
                }
            }
        }
    }
?>