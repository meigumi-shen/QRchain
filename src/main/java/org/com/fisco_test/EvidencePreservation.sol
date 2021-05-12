pragma solidity >=0.4.22 <0.7.0;

contract EvidencePreservation{
  
    uint256 Serial=1;
    struct Evidence{
        address master;
        uint256 serial_of_evi;//该证据的序号
        uint256 previous_evi;//该证据所属用户的上一条上传证据
        uint256 next_evi;//该证据所属用户的下一条上传证据
        bytes32 data;
    }
    struct User{
        bool is_exist;
        string name;
        address useraddr;
        uint256 total_evi;
        uint256 last_evi;//该用户上传的最后一条证据，
        
    }
    //地址映射用户
    mapping(address=>User) user;
    //序号映射证据
    mapping(uint256=>Evidence)evidence;
    
    address private administrator;
    //添加管理员
    function addAdministrator()public returns(bytes32){
        administrator=msg.sender;
        return bytes32(uint256(administrator));
    }
    
    
    //注册账户（0:失败，1:成功）
    function register(address user_address,string memory name)public returns(int256){
        if(!(msg.sender==administrator)){
            return 0;
        }
        user[user_address].is_exist=true;
        user[user_address].name=name;
        user[user_address].useraddr=user_address;
        user[user_address].total_evi=0;
        user_address;
        return 1;
    }
    //创建新证据
    function createEvidence(bytes32 data)public returns(uint256){
        require(user[msg.sender].is_exist);
        if(user[msg.sender].total_evi==0){
            user[msg.sender].last_evi=makeEvidence(data,Serial);
            
        }
        else{
            evidence[user[msg.sender].last_evi].next_evi=makeEvidence(data,user[msg.sender].last_evi);
            user[msg.sender].last_evi=evidence[user[msg.sender].last_evi].next_evi;
        }
        user[msg.sender].total_evi++;
        return user[msg.sender].last_evi;
    }
    function makeEvidence(bytes32 data,uint256 p_evi_serial)private returns(uint256){
        evidence[Serial].master=msg.sender;
        evidence[Serial].data=data;
        evidence[Serial].serial_of_evi=Serial;
        evidence[Serial].previous_evi=p_evi_serial;
        evidence[Serial].next_evi=0; 
       
        return Serial++;
    }
    //通过序号查找证据
    function retrieveEvidenceBySerial(uint256 serial_evi)public returns(string memory,bytes32,bytes32){
        require(  (msg.sender==evidence[serial_evi].master) ||(msg.sender==administrator)  );
        bytes memory bytedata = new bytes(32);
        bytedata = bytes32toBytes(evidence[serial_evi].data);
        
        bytes memory byteaddr = new bytes(32);
        byteaddr = bytes32toBytes(bytes32(uint256(evidence[serial_evi].master)));
        
        return (strConcat(strConcat("master is ",user[evidence[serial_evi].master].name),strConcat(",address is ",string(byteaddr))),bytes32(uint(evidence[serial_evi].master)),evidence[serial_evi].data);
    }
    //bytes32 -> bytes，工具类
    function bytes32toBytes(bytes32 _input)private returns(bytes memory){
        bytes memory _output = new bytes(32);
        uint256 i;
        for(i=0;i<32;i++){
            _output[i]=_input[i];
        }
        return _output;
    }
    //字符串连接，工具类
    function strConcat(string memory _a, string memory _b) internal returns (string memory){
        bytes memory _ba = bytes(_a);
        bytes memory _bb = bytes(_b);
        string memory ret = new string(_ba.length + _bb.length);
        bytes memory bret = bytes(ret);
        uint k = 0;
        uint i;
        for (i = 0; i < _ba.length; i++)bret[k++] = _ba[i];
        for (i = 0; i < _bb.length; i++) bret[k++] = _bb[i];
        return string(ret);
   } 
}    
