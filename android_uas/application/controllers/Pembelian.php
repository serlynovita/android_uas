<?php

require APPPATH . '/libraries/REST_Controller.php';

class Pembelian extends REST_Controller {

	function __construct($config = 'rest') {
        parent::__construct($config);
    }

    function user_get(){
    	$get_transaksi = $this->db->query("SELECT pemb.id_pembelian, pemb.id_pembeli, pemb.tanggal_beli, pemb.total_harga, pemb.id_tiket FROM pembeli, pembelian pemb, tiket WHERE pemb.id_pembeli=pembeli.id_pembeli AND pemb.id_tiket=tiket.id_tiket")->result();
    	$this->response(array("status"=>"success","result"=>$get_transaksi));
    }

    function user_post(){
    	$data_pembelian = array(
    		'id_pembelian' => $this->post('id_pembelian'),
    		'id_pembeli' => $this->post('id_pembeli'),
    		'tanggal_beli' => $this->post('tanggal_beli'),
    		'total_harga' => $this->post('total_harga'),
    		'id_tiket' => $this->post('id_tiket')
    	);

    	if(empty($data_pembelian['id_pembelian'])){
    		$this->response(array('status'=>'fail','message'=>'id_pembelian kosong'));
    	}
    	else{
    		$getId = $this->db->query("SELECT id_pembelian FROM pembelian  where id_pembelian='".$data_pembelian['id_pembelian']."'")->result();
    		if(empty($getId)){
    			if(empty($data_pembelian['id_pembeli'])){
    				$this->response(array('status'=>'fail','message'=>'id_pembeli kosong'));
    			}
    			else if(empty($data_pembelian['total_harga'])){
		    		$this->response(array('status'=>'fail','message'=>'total_harga kosong'));
		    	}
		    	else if(empty($data_pembelian['id_tiket'])){
		    		$this->response(array('status'=>'fail','message'=>'id_tiket kosong'));
    			}
    			else if(empty($data_pembelian['tanggal_beli'])){
    				$this->response(array('status'=>'fail','message'=>'tanggal_beli kosong'));
    			}
    			else{
    				$getIdPembeli = $this->db->query("SELECT id_pembeli FROM pembeli WHERE id_pembeli='".$data_pembelian['id_pembeli']."'")->result();
    				$getIdTiket = $this->db->query("SELECT id_tiket FROM tiket WHERE id_tiket='".$data_pembelian['id_tiket']."'")->result();
    				$message = "";

    				if(empty($getIdPembeli)) $message.= "id_pembeli tidak ada/salah";
    				if(empty($getIdTiket)){
    					if(empty($message)){
    						$message.="id_tiket tidak ada/salah";
    					}
    					else{
    						$message.="dan id_tiket tidak ada/salah";
    					}
    				}

    				if(empty($message)){
    					$insert = $this->db->insert('pembelian',$data_pembelian);
    					if($insert){
    						$this->response(array('status'=>'success','result'=>$data_pembelian,'message'=>$insert));
    					}
    				}
    				else{
    					$this->response(array('status'=>'fail','message'=>$message));
    				}
    			}
    		}
    		else{
    			$this->response(array('status'=>'fail','message'=>'id_pembelian sudah ada'));
    		}
    	}
    }

    function user_put(){
    	$data_pembelian = array(
    		'id_pembelian' => $this->put('id_pembelian'),
    		'id_pembeli' => $this->put('id_pembeli'),
    		'tanggal_beli' => $this->put('tanggal_beli'),
    		'total_harga' => $this->put('total_harga'),
    		'id_tiket' => $this->put('id_tiket')
    	);

    	if(empty($data_pembelian['id_pembelian'])){
    		$this->response(array('status'=>'fail','message'=>'id_pembelian kosong'));
    	}
    	else{
    		$getId = $this->db->query("SELECT id_pembelian FROM pembelian  where id_pembelian='".$data_pembelian['id_pembelian']."'")->result();
    		if(isset($getId)){
    			if(empty($data_pembelian['id_pembeli'])){
    				$this->response(array('status'=>'fail','message'=>'id_pembeli kosong'));
    			}
    			else if(empty($data_pembelian['total_harga'])){
		    		$this->response(array('status'=>'fail','message'=>'total_harga kosong'));
		    	}
		    	else if(empty($data_pembelian['id_tiket'])){
		    		$this->response(array('status'=>'fail','message'=>'id_tiket kosong'));
    			}
    			else if(empty($data_pembelian['tanggal_beli'])){
    				$this->response(array('status'=>'fail','message'=>'tanggal_beli kosong'));
    			}
    			else{
    				$getIdPembeli = $this->db->query("SELECT id_pembeli FROM pembeli WHERE id_pembeli='".$data_pembelian['id_pembeli']."'")->result();
    				$getIdTiket = $this->db->query("SELECT id_tiket FROM tiket WHERE id_tiket='".$data_pembelian['id_tiket']."'")->result();
    				$message = "";

    				if(empty($getIdPembeli)) $message.= "id_pembeli tidak ada/salah";
    				if(empty($getIdTiket)){
    					if(empty($message)){
    						$message.="id_tiket tidak ada/salah";
    					}
    					else{
    						$message.="dan id_tiket tidak ada/salah";
    					}
    				}

    				if(empty($message)){
    					$this->db->where('id_pembelian',$data_pembelian['id_pembelian']);
    					$update = $this->db->update('pembelian',$data_pembelian);
    					if($update){
    						$this->response(array('status'=>'success','result'=>$data_pembelian,'message'=>$update));
    					}
    				}
    				else{
    					$this->response(array('status'=>'fail','message'=>$message));
    				}
    			}
    		}
    		else{
    			$this->response(array('status'=>'fail','message'=>'id_pembelian tidak ada yang cocok'));
    		}
    	}
    }

    function user_delete(){
    	$id_pembelian = $this->delete('id_pembelian');
    	if(empty($id_pembelian)){
    		$this->response(array('status'=>'fail','message'=>'id_pembelian harus diisi'));
    	}
    	else{
    		$this->db->where('id_pembelian',$id_pembelian);
    		$delete = $this->db->delete('pembelian');
    		if($this->db->affected_rows()){
    			$this->response(array('status'=>'success','message'=>"berhasil delete dengan id_pembelian=".$id_pembelian));
    		}
    		else{
    			$this->response(array('status'=>'success','message'=>'id_pembelian tidak dalam database'));
    		}
    	}
    }
}
