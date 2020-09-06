package com.chaucer.blockchain.contract;

/**
 * @author Chaucer
 * @date 2019-10-14 13:50
 */
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
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

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 4.3.0.
 */
public class TransactionContract extends Contract {
    private static final String BINARY = "6080604052600060015534801561001557600080fd5b50610571806100256000396000f300608060405260043610610062576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff1680631083fa23146100675780632cb7a3a0146101165780636747f39114610141578063b21bf7fa146101e7575b600080fd5b34801561007357600080fd5b50610114600480360381019080803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290803590602001908201803590602001908080601f016020809104026020016040519081016040528093929190818152602001838380828437820191505050505050919291929050505061028d565b005b34801561012257600080fd5b5061012b610310565b6040518082815260200191505060405180910390f35b34801561014d57600080fd5b5061016c6004803603810190808035906020019092919050505061031c565b6040518080602001828103825283818151815260200191508051906020019080838360005b838110156101ac578082015181840152602081019050610191565b50505050905090810190601f1680156101d95780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b3480156101f357600080fd5b50610212600480360381019080803590602001909291905050506103de565b6040518080602001828103825283818151815260200191508051906020019080838360005b83811015610252578082015181840152602081019050610237565b50505050905090810190601f16801561027f5780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b60006040805190810160405280848152602001838152509080600181540180825580915050906001820390600052602060002090600202016000909192909190915060008201518160000190805190602001906102eb9291906104a0565b5060208201518160010190805190602001906103089291906104a0565b505050505050565b60008080549050905090565b606060008281548110151561032d57fe5b90600052602060002090600202016001018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156103d25780601f106103a7576101008083540402835291602001916103d2565b820191906000526020600020905b8154815290600101906020018083116103b557829003601f168201915b50505050509050919050565b60606000828154811015156103ef57fe5b90600052602060002090600202016000018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156104945780601f1061046957610100808354040283529160200191610494565b820191906000526020600020905b81548152906001019060200180831161047757829003601f168201915b50505050509050919050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106104e157805160ff191683800117855561050f565b8280016001018555821561050f579182015b8281111561050e5782518255916020019190600101906104f3565b5b50905061051c9190610520565b5090565b61054291905b8082111561053e576000816000905550600101610526565b5090565b905600a165627a7a72305820cb8618adb8e6a2762f9a7020f7b8ba69398bba8f38b925a2bea3dc3932148dab0029\n";

    public static final String FUNC_ADDTRANSACTION = "addTransaction";

    public static final String FUNC_GETLABEL = "getLabel";

    public static final String FUNC_GETTIMESTAMP = "getTimeStamp";

    public static final String FUNC_RETURNTOTAL = "returnTotal";

    @Deprecated
    protected TransactionContract(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected TransactionContract(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected TransactionContract(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected TransactionContract(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteCall<TransactionReceipt> addTransaction(String label, String timeStamp) {
        final Function function = new Function(
                FUNC_ADDTRANSACTION,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(label),
                        new org.web3j.abi.datatypes.Utf8String(timeStamp)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<String> getLabel(BigInteger id) {
        final Function function = new Function(FUNC_GETLABEL,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(id)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<String> getTimeStamp(BigInteger id) {
        final Function function = new Function(FUNC_GETTIMESTAMP,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(id)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<BigInteger> returnTotal() {
        final Function function = new Function(FUNC_RETURNTOTAL,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    @Deprecated
    public static TransactionContract load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new TransactionContract(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static TransactionContract load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new TransactionContract(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static TransactionContract load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new TransactionContract(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static TransactionContract load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new TransactionContract(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<TransactionContract> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(TransactionContract.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<TransactionContract> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(TransactionContract.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<TransactionContract> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(TransactionContract.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<TransactionContract> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(TransactionContract.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }
}
