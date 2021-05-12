package org.com.fisco;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.fisco.bcos.sdk.abi.FunctionReturnDecoder;
import org.fisco.bcos.sdk.abi.TypeReference;
import org.fisco.bcos.sdk.abi.datatypes.Address;
import org.fisco.bcos.sdk.abi.datatypes.Function;
import org.fisco.bcos.sdk.abi.datatypes.Type;
import org.fisco.bcos.sdk.abi.datatypes.Utf8String;
import org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32;
import org.fisco.bcos.sdk.abi.datatypes.generated.Int256;
import org.fisco.bcos.sdk.abi.datatypes.generated.Uint256;
import org.fisco.bcos.sdk.abi.datatypes.generated.tuples.generated.Tuple1;
import org.fisco.bcos.sdk.abi.datatypes.generated.tuples.generated.Tuple2;
import org.fisco.bcos.sdk.abi.datatypes.generated.tuples.generated.Tuple3;
import org.fisco.bcos.sdk.client.Client;
import org.fisco.bcos.sdk.contract.Contract;
import org.fisco.bcos.sdk.crypto.CryptoSuite;
import org.fisco.bcos.sdk.crypto.keypair.CryptoKeyPair;
import org.fisco.bcos.sdk.model.CryptoType;
import org.fisco.bcos.sdk.model.TransactionReceipt;
import org.fisco.bcos.sdk.model.callback.TransactionCallback;
import org.fisco.bcos.sdk.transaction.model.exception.ContractException;

@SuppressWarnings("unchecked")
public class EvidencePreservation extends Contract {
    public static final String[] BINARY_ARRAY = {"6080604052600160005534801561001557600080fd5b50610fac806100256000396000f300608060405260043610610062576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff16806312872064146100675780631f7b77e11461012b57806332434a2e1461015e57806340d5adf8146101fb575b600080fd5b34801561007357600080fd5b5061009260048036038101908080359060200190929190505050610240565b604051808060200184600019166000191681526020018360001916600019168152602001828103825285818151815260200191508051906020019080838360005b838110156100ee5780820151818401526020810190506100d3565b50505050905090810190601f16801561011b5780820380516001836020036101000a031916815260200191505b5094505050505060405180910390f35b34801561013757600080fd5b50610140610605565b60405180826000191660001916815260200191505060405180910390f35b34801561016a57600080fd5b506101e5600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290505050610689565b6040518082815260200191505060405180910390f35b34801561020757600080fd5b5061022a6004803603810190808035600019169060200190929190505050610870565b6040518082815260200191505060405180910390f35b60606000806060806002600087815260200190815260200160002060000160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff1614806103055750600360009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff16145b151561031057600080fd5b60206040519080825280601f01601f1916602001820160405280156103445781602001602082028038833980820191505090505b5091506103666002600088815260200190815260200160002060040154610b53565b915060206040519080825280601f01601f19166020018201604052801561039c5781602001602082028038833980820191505090505b5090506103f76002600088815260200190815260200160002060000160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16600102610b53565b905061058f61054b6040805190810160405280600a81526020017f6d6173746572206973200000000000000000000000000000000000000000000081525060016000600260008c815260200190815260200160002060000160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206001018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156105415780601f1061051657610100808354040283529160200191610541565b820191906000526020600020905b81548152906001019060200180831161052457829003601f168201915b5050505050610c23565b61058a6040805190810160405280600c81526020017f2c6164647265737320697320000000000000000000000000000000000000000081525084610c23565b610c23565b6002600088815260200190815260200160002060000160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16600102600260008981526020019081526020016000206004015494509450945050509193909250565b600033600360006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550600360009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16600102905090565b6000600360009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff161415156106eb576000905061086a565b60018060008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060000160006101000a81548160ff02191690831515021790555081600160008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600101908051906020019061079b929190610edb565b5082600160008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060020160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506000600160008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060030181905550600190505b92915050565b6000600160003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060000160009054906101000a900460ff1615156108cd57600080fd5b6000600160003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060030154141561096f5761092482600054610df4565b600160003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060040181905550610ab7565b6109bb82600160003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060040154610df4565b60026000600160003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206004015481526020019081526020016000206003018190555060026000600160003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060040154815260200190815260200160002060030154600160003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600401819055505b600160003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060030160008154809291906001019190505550600160003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600401549050919050565b606080600060206040519080825280601f01601f191660200182016040528015610b8c5781602001602082028038833980820191505090505b509150600090505b6020811015610c19578381602081101515610bab57fe5b1a7f0100000000000000000000000000000000000000000000000000000000000000028282815181101515610bdc57fe5b9060200101907effffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916908160001a9053508080600101915050610b94565b8192505050919050565b606080606080606060008088955087945084518651016040519080825280601f01601f191660200182016040528015610c6b5781602001602082028038833980820191505090505b50935083925060009150600090505b8551811015610d2d578581815181101515610c9157fe5b9060200101517f010000000000000000000000000000000000000000000000000000000000000090047f0100000000000000000000000000000000000000000000000000000000000000028383806001019450815181101515610cf057fe5b9060200101907effffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916908160001a9053508080600101915050610c7a565b600090505b8451811015610de5578481815181101515610d4957fe5b9060200101517f010000000000000000000000000000000000000000000000000000000000000090047f0100000000000000000000000000000000000000000000000000000000000000028383806001019450815181101515610da857fe5b9060200101907effffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916908160001a9053508080600101915050610d32565b83965050505050505092915050565b600033600260008054815260200190815260200160002060000160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550826002600080548152602001908152602001600020600401816000191690555060005460026000805481526020019081526020016000206001018190555081600260008054815260200190815260200160002060020181905550600060026000805481526020019081526020016000206003018190555060008081548092919060010191905055905092915050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10610f1c57805160ff1916838001178555610f4a565b82800160010185558215610f4a579182015b82811115610f49578251825591602001919060010190610f2e565b5b509050610f579190610f5b565b5090565b610f7d91905b80821115610f79576000816000905550600101610f61565b5090565b905600a165627a7a7230582082d8aba8fc2772d19e937dd62a4edaf2192a9facb371796027a7ad893d7dc4090029"};

    public static final String BINARY = String.join("", BINARY_ARRAY);

    public static final String[] SM_BINARY_ARRAY = {"6080604052600160005534801561001557600080fd5b50610fac806100256000396000f300608060405260043610610062576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff16806318efc1c11461006757806370002e16146100ac578063856101ec14610170578063d0a36c0a1461020d575b600080fd5b34801561007357600080fd5b506100966004803603810190808035600019169060200190929190505050610240565b6040518082815260200191505060405180910390f35b3480156100b857600080fd5b506100d760048036038101908080359060200190929190505050610523565b604051808060200184600019166000191681526020018360001916600019168152602001828103825285818151815260200191508051906020019080838360005b83811015610133578082015181840152602081019050610118565b50505050905090810190601f1680156101605780820380516001836020036101000a031916815260200191505b5094505050505060405180910390f35b34801561017c57600080fd5b506101f7600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803590602001908201803590602001908080601f01602080910402602001604051908101604052809392919081815260200183838082843782019150505050505091929192905050506108e8565b6040518082815260200191505060405180910390f35b34801561021957600080fd5b50610222610acf565b60405180826000191660001916815260200191505060405180910390f35b6000600160003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060000160009054906101000a900460ff16151561029d57600080fd5b6000600160003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060030154141561033f576102f482600054610b53565b600160003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060040181905550610487565b61038b82600160003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060040154610b53565b60026000600160003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206004015481526020019081526020016000206003018190555060026000600160003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060040154815260200190815260200160002060030154600160003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600401819055505b600160003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060030160008154809291906001019190505550600160003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600401549050919050565b60606000806060806002600087815260200190815260200160002060000160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff1614806105e85750600360009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff16145b15156105f357600080fd5b60206040519080825280601f01601f1916602001820160405280156106275781602001602082028038833980820191505090505b5091506106496002600088815260200190815260200160002060040154610c3a565b915060206040519080825280601f01601f19166020018201604052801561067f5781602001602082028038833980820191505090505b5090506106da6002600088815260200190815260200160002060000160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16600102610c3a565b905061087261082e6040805190810160405280600a81526020017f6d6173746572206973200000000000000000000000000000000000000000000081525060016000600260008c815260200190815260200160002060000160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206001018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156108245780601f106107f957610100808354040283529160200191610824565b820191906000526020600020905b81548152906001019060200180831161080757829003601f168201915b5050505050610d0a565b61086d6040805190810160405280600c81526020017f2c6164647265737320697320000000000000000000000000000000000000000081525084610d0a565b610d0a565b6002600088815260200190815260200160002060000160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16600102600260008981526020019081526020016000206004015494509450945050509193909250565b6000600360009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff1614151561094a5760009050610ac9565b60018060008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060000160006101000a81548160ff02191690831515021790555081600160008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060010190805190602001906109fa929190610edb565b5082600160008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060020160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506000600160008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060030181905550600190505b92915050565b600033600360006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550600360009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16600102905090565b600033600260008054815260200190815260200160002060000160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550826002600080548152602001908152602001600020600401816000191690555060005460026000805481526020019081526020016000206001018190555081600260008054815260200190815260200160002060020181905550600060026000805481526020019081526020016000206003018190555060008081548092919060010191905055905092915050565b606080600060206040519080825280601f01601f191660200182016040528015610c735781602001602082028038833980820191505090505b509150600090505b6020811015610d00578381602081101515610c9257fe5b1a7f0100000000000000000000000000000000000000000000000000000000000000028282815181101515610cc357fe5b9060200101907effffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916908160001a9053508080600101915050610c7b565b8192505050919050565b606080606080606060008088955087945084518651016040519080825280601f01601f191660200182016040528015610d525781602001602082028038833980820191505090505b50935083925060009150600090505b8551811015610e14578581815181101515610d7857fe5b9060200101517f010000000000000000000000000000000000000000000000000000000000000090047f0100000000000000000000000000000000000000000000000000000000000000028383806001019450815181101515610dd757fe5b9060200101907effffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916908160001a9053508080600101915050610d61565b600090505b8451811015610ecc578481815181101515610e3057fe5b9060200101517f010000000000000000000000000000000000000000000000000000000000000090047f0100000000000000000000000000000000000000000000000000000000000000028383806001019450815181101515610e8f57fe5b9060200101907effffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916908160001a9053508080600101915050610e19565b83965050505050505092915050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10610f1c57805160ff1916838001178555610f4a565b82800160010185558215610f4a579182015b82811115610f49578251825591602001919060010190610f2e565b5b509050610f579190610f5b565b5090565b610f7d91905b80821115610f79576000816000905550600101610f61565b5090565b905600a165627a7a72305820aa3c73214c2624a09f23c980510db4088f767e0fa06d6e0ba1e7ae3f64c1b5770029"};

    public static final String SM_BINARY = String.join("", SM_BINARY_ARRAY);

    public static final String[] ABI_ARRAY = {"[{\"constant\":false,\"inputs\":[{\"name\":\"serial_evi\",\"type\":\"uint256\"}],\"name\":\"retrieveEvidenceBySerial\",\"outputs\":[{\"name\":\"\",\"type\":\"string\"},{\"name\":\"\",\"type\":\"bytes32\"},{\"name\":\"\",\"type\":\"bytes32\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[],\"name\":\"addAdministrator\",\"outputs\":[{\"name\":\"\",\"type\":\"bytes32\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"user_address\",\"type\":\"address\"},{\"name\":\"name\",\"type\":\"string\"}],\"name\":\"register\",\"outputs\":[{\"name\":\"\",\"type\":\"int256\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"data\",\"type\":\"bytes32\"}],\"name\":\"createEvidence\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"}]"};

    public static final String ABI = String.join("", ABI_ARRAY);

    public static final String FUNC_RETRIEVEEVIDENCEBYSERIAL = "retrieveEvidenceBySerial";

    public static final String FUNC_ADDADMINISTRATOR = "addAdministrator";

    public static final String FUNC_REGISTER = "register";

    public static final String FUNC_CREATEEVIDENCE = "createEvidence";

    protected EvidencePreservation(String contractAddress, Client client, CryptoKeyPair credential) {
        super(getBinary(client.getCryptoSuite()), contractAddress, client, credential);
    }

    public static String getBinary(CryptoSuite cryptoSuite) {
        return (cryptoSuite.getCryptoTypeConfig() == CryptoType.ECDSA_TYPE ? BINARY : SM_BINARY);
    }

    public TransactionReceipt retrieveEvidenceBySerial(BigInteger serial_evi) {
        final Function function = new Function(
                FUNC_RETRIEVEEVIDENCEBYSERIAL, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.generated.Uint256(serial_evi)), 
                Collections.<TypeReference<?>>emptyList());
        return executeTransaction(function);
    }

    public void retrieveEvidenceBySerial(BigInteger serial_evi, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_RETRIEVEEVIDENCEBYSERIAL, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.generated.Uint256(serial_evi)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String getSignedTransactionForRetrieveEvidenceBySerial(BigInteger serial_evi) {
        final Function function = new Function(
                FUNC_RETRIEVEEVIDENCEBYSERIAL, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.generated.Uint256(serial_evi)), 
                Collections.<TypeReference<?>>emptyList());
        return createSignedTransaction(function);
    }

    public Tuple1<BigInteger> getRetrieveEvidenceBySerialInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_RETRIEVEEVIDENCEBYSERIAL, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple1<BigInteger>(

                (BigInteger) results.get(0).getValue()
                );
    }

    public Tuple3<String, byte[], byte[]> getRetrieveEvidenceBySerialOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_RETRIEVEEVIDENCEBYSERIAL, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Bytes32>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple3<String, byte[], byte[]>(

                (String) results.get(0).getValue(), 
                (byte[]) results.get(1).getValue(), 
                (byte[]) results.get(2).getValue()
                );
    }

    public TransactionReceipt addAdministrator() {
        final Function function = new Function(
                FUNC_ADDADMINISTRATOR, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeTransaction(function);
    }

    public void addAdministrator(TransactionCallback callback) {
        final Function function = new Function(
                FUNC_ADDADMINISTRATOR, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String getSignedTransactionForAddAdministrator() {
        final Function function = new Function(
                FUNC_ADDADMINISTRATOR, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return createSignedTransaction(function);
    }

    public Tuple1<byte[]> getAddAdministratorOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_ADDADMINISTRATOR, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple1<byte[]>(

                (byte[]) results.get(0).getValue()
                );
    }

    public TransactionReceipt register(String user_address, String name) {
        final Function function = new Function(
                FUNC_REGISTER, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.Address(user_address), 
                new org.fisco.bcos.sdk.abi.datatypes.Utf8String(name)), 
                Collections.<TypeReference<?>>emptyList());
        return executeTransaction(function);
    }

    public void register(String user_address, String name, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_REGISTER, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.Address(user_address), 
                new org.fisco.bcos.sdk.abi.datatypes.Utf8String(name)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String getSignedTransactionForRegister(String user_address, String name) {
        final Function function = new Function(
                FUNC_REGISTER, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.Address(user_address), 
                new org.fisco.bcos.sdk.abi.datatypes.Utf8String(name)), 
                Collections.<TypeReference<?>>emptyList());
        return createSignedTransaction(function);
    }

    public Tuple2<String, String> getRegisterInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_REGISTER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Utf8String>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple2<String, String>(

                (String) results.get(0).getValue(), 
                (String) results.get(1).getValue()
                );
    }

    public Tuple1<BigInteger> getRegisterOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_REGISTER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple1<BigInteger>(

                (BigInteger) results.get(0).getValue()
                );
    }

    public TransactionReceipt createEvidence(byte[] data) {
        final Function function = new Function(
                FUNC_CREATEEVIDENCE, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32(data)), 
                Collections.<TypeReference<?>>emptyList());
        return executeTransaction(function);
    }

    public void createEvidence(byte[] data, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_CREATEEVIDENCE, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32(data)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String getSignedTransactionForCreateEvidence(byte[] data) {
        final Function function = new Function(
                FUNC_CREATEEVIDENCE, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32(data)), 
                Collections.<TypeReference<?>>emptyList());
        return createSignedTransaction(function);
    }

    public Tuple1<byte[]> getCreateEvidenceInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_CREATEEVIDENCE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple1<byte[]>(

                (byte[]) results.get(0).getValue()
                );
    }

    public Tuple1<BigInteger> getCreateEvidenceOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_CREATEEVIDENCE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple1<BigInteger>(

                (BigInteger) results.get(0).getValue()
                );
    }

    public static EvidencePreservation load(String contractAddress, Client client, CryptoKeyPair credential) {
        return new EvidencePreservation(contractAddress, client, credential);
    }

    public static EvidencePreservation deploy(Client client, CryptoKeyPair credential) throws ContractException {
        return deploy(EvidencePreservation.class, client, credential, getBinary(client.getCryptoSuite()), "");
    }
}
