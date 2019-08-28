package com.chaucer.blockchain.contract;

import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 4.2.0.
 */
public class DataStorageContract extends Contract {
    private static final String BINARY = "6080604052600060015534801561001557600080fd5b50610552806100256000396000f30060806040526004361061006c5763ffffffff7c01000000000000000000000000000000000000000000000000000000006000350416630178fe3f81146100715780632cb7a3a0146100fe5780633e4aaec9146101255780636747f391146101fc578063ce7c9ef414610214575b600080fd5b34801561007d57600080fd5b5061008960043561022c565b6040805160208082528351818301528351919283929083019185019080838360005b838110156100c35781810151838201526020016100ab565b50505050905090810190601f1680156100f05780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b34801561010a57600080fd5b506101136102dc565b60408051918252519081900360200190f35b34801561013157600080fd5b506040805160206004803580820135601f81018490048402850184019095528484526101fa94369492936024939284019190819084018382808284375050604080516020601f89358b018035918201839004830284018301909452808352979a99988101979196509182019450925082915084018382808284375050604080516020601f89358b018035918201839004830284018301909452808352979a9998810197919650918201945092508291508401838280828437509497506102e39650505050505050565b005b34801561020857600080fd5b50610089600435610385565b34801561022057600080fd5b50610089600435610403565b606060008281548110151561023d57fe5b6000918252602091829020600390910201805460408051601f60026000196101006001871615020190941693909304928301859004850281018501909152818152928301828280156102d05780601f106102a5576101008083540402835291602001916102d0565b820191906000526020600020905b8154815290600101906020018083116102b357829003601f168201915b50505050509050919050565b6000545b90565b604080516060810182528481526020808201859052918101839052600080546001810180835591805282518051929460039092027f290decd9548b62a8d60345a988386fc84ba6bc95484008f6362f93160ef3e5630192610347928492019061048e565b506020828101518051610360926001850192019061048e565b506040820151805161037c91600284019160209091019061048e565b50505050505050565b606060008281548110151561039657fe5b600091825260209182902060026003909202018101805460408051601f6000196101006001861615020190931694909404918201859004850284018501905280835291929091908301828280156102d05780601f106102a5576101008083540402835291602001916102d0565b606060008281548110151561041457fe5b90600052602060002090600302016001018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156102d05780601f106102a5576101008083540402835291602001916102d0565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106104cf57805160ff19168380011785556104fc565b828001600101855582156104fc579182015b828111156104fc5782518255916020019190600101906104e1565b5061050892915061050c565b5090565b6102e091905b8082111561050857600081556001016105125600a165627a7a72305820e3f689d7817d265256918eabb9e6f12dbca19b56309be092ce943330d32b28770029";

    public static final String FUNC_GETDATA = "getData";

    public static final String FUNC_RETURNTOTAL = "returnTotal";

    public static final String FUNC_ADDDATA = "addData";

    public static final String FUNC_GETTIMESTAMP = "getTimeStamp";

    public static final String FUNC_GETPSEID = "getPseId";

    @Deprecated
    protected DataStorageContract(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected DataStorageContract(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected DataStorageContract(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected DataStorageContract(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteCall<String> getData(BigInteger id) {
        final Function function = new Function(FUNC_GETDATA, 
                Arrays.<Type>asList(new Uint256(id)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<BigInteger> returnTotal() {
        final Function function = new Function(FUNC_RETURNTOTAL,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> addData(String data, String pseId, String timeStamp) {
        final Function function = new Function(
                FUNC_ADDDATA,
                Arrays.<Type>asList(new Utf8String(data),
                new Utf8String(pseId),
                new Utf8String(timeStamp)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<String> getTimeStamp(BigInteger id) {
        final Function function = new Function(FUNC_GETTIMESTAMP,
                Arrays.<Type>asList(new Uint256(id)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<String> getPseId(BigInteger id) {
        final Function function = new Function(FUNC_GETPSEID,
                Arrays.<Type>asList(new Uint256(id)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    @Deprecated
    public static DataStorageContract load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new DataStorageContract(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static DataStorageContract load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new DataStorageContract(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static DataStorageContract load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new DataStorageContract(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static DataStorageContract load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new DataStorageContract(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<DataStorageContract> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(DataStorageContract.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<DataStorageContract> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(DataStorageContract.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<DataStorageContract> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(DataStorageContract.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<DataStorageContract> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(DataStorageContract.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }
}
