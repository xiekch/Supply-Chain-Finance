package com.xiekch.server.solidity;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.fisco.bcos.channel.client.TransactionSucCallback;
import org.fisco.bcos.channel.event.filter.EventLogPushWithDecodeCallback;
import org.fisco.bcos.web3j.abi.EventEncoder;
import org.fisco.bcos.web3j.abi.FunctionEncoder;
import org.fisco.bcos.web3j.abi.FunctionReturnDecoder;
import org.fisco.bcos.web3j.abi.TypeReference;
import org.fisco.bcos.web3j.abi.datatypes.Address;
import org.fisco.bcos.web3j.abi.datatypes.Bool;
import org.fisco.bcos.web3j.abi.datatypes.Event;
import org.fisco.bcos.web3j.abi.datatypes.Function;
import org.fisco.bcos.web3j.abi.datatypes.Type;
import org.fisco.bcos.web3j.abi.datatypes.Utf8String;
import org.fisco.bcos.web3j.abi.datatypes.generated.Int256;
import org.fisco.bcos.web3j.abi.datatypes.generated.Uint256;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.core.RemoteCall;
import org.fisco.bcos.web3j.protocol.core.methods.response.Log;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.fisco.bcos.web3j.tuples.generated.Tuple1;
import org.fisco.bcos.web3j.tuples.generated.Tuple2;
import org.fisco.bcos.web3j.tuples.generated.Tuple3;
import org.fisco.bcos.web3j.tuples.generated.Tuple4;
import org.fisco.bcos.web3j.tuples.generated.Tuple7;
import org.fisco.bcos.web3j.tx.Contract;
import org.fisco.bcos.web3j.tx.TransactionManager;
import org.fisco.bcos.web3j.tx.gas.ContractGasProvider;
import org.fisco.bcos.web3j.tx.txdecode.TransactionDecoder;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.fisco.bcos.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version none.
 */
@SuppressWarnings("unchecked")
public class AR extends Contract {
    public static String BINARY = "60806040523480156200001157600080fd5b50604051620024b3380380620024b3833981018060405281019080805182019291906020018051906020019092919080519060200190929190505050336000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060e060405190810160405280848152602001838152602001828152602001600081526020016000815260200160011515815260200160011515815250600260008060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600082015181600001908051906020019062000147929190620001c4565b506020820151816001015560408201518160020155606082015181600301556080820151816004015560a08201518160050160006101000a81548160ff02191690831515021790555060c08201518160050160016101000a81548160ff021916908315150217905550905050600060018190555050505062000273565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106200020757805160ff191683800117855562000238565b8280016001018555821562000238579182015b82811115620002375782518255916020019190600101906200021a565b5b5090506200024791906200024b565b5090565b6200027091905b808211156200026c57600081600090555060010162000252565b5090565b90565b61223080620002836000396000f3006080604052600436106100ba576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff1680630411b252146100bf5780630f7ee1ec1461014a57806323060627146101fc578063355e6ce81461025357806337cf1bab146103415780634f1c8e801461039857806364b4aba5146103ef5780636bce989b1461048c5780639549a52c1461050d578063b63e6ac3146105a3578063b8146acc1461064a578063c4076876146106e7575b600080fd5b3480156100cb57600080fd5b50610134600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803573ffffffffffffffffffffffffffffffffffffffff1690602001909291908035906020019092919080359060200190929190505050610734565b6040518082815260200191505060405180910390f35b34801561015657600080fd5b5061017560048036038101908080359060200190929190505050610b6b565b604051808573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018381526020018215151515815260200194505050505060405180910390f35b34801561020857600080fd5b5061023d600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610be8565b6040518082815260200191505060405180910390f35b34801561025f57600080fd5b50610294600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610cf8565b60405180806020018881526020018781526020018681526020018581526020018415151515815260200183151515158152602001828103825289818151815260200191508051906020019080838360005b838110156103005780820151818401526020810190506102e5565b50505050905090810190601f16801561032d5780820380516001836020036101000a031916815260200191505b509850505050505050505060405180910390f35b34801561034d57600080fd5b50610382600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610dec565b6040518082815260200191505060405180910390f35b3480156103a457600080fd5b506103d9600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610efc565b6040518082815260200191505060405180910390f35b3480156103fb57600080fd5b5061048a600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290803590602001909291908035906020019092919050505061100c565b005b34801561049857600080fd5b506104f7600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803573ffffffffffffffffffffffffffffffffffffffff1690602001909291908035906020019092919050505061120d565b6040518082815260200191505060405180910390f35b34801561051957600080fd5b50610582600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803573ffffffffffffffffffffffffffffffffffffffff1690602001909291908035906020019092919080359060200190929190505050611541565b60405180831515151581526020018281526020019250505060405180910390f35b3480156105af57600080fd5b506105ce60048036038101908080359060200190929190505050611a1e565b604051808473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001828152602001935050505060405180910390f35b34801561065657600080fd5b506106e5600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803590602001908201803590602001908080601f01602080910402602001604051908101604052809392919081815260200183838082843782019150505050505091929192908035906020019092919080359060200190929190505050611b2f565b005b3480156106f357600080fd5b50610732600480360381019080803573ffffffffffffffffffffffffffffffffffffffff16906020019092919080359060200190929190505050611d30565b005b60008060006001915060001515600260008873ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060050160019054906101000a900460ff161515141561079e57600091505b60001515600260008973ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060050160019054906101000a900460ff16151514156107ff57600091505b60015485111561080e57600091505b8673ffffffffffffffffffffffffffffffffffffffff166003600087815260200190815260200160002060010160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1614151561087e57600091505b83600360008781526020019081526020016000206002015410156108a157600091505b6001821415610b20576003600086815260200190815260200160002060000160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16905083600360008781526020019081526020016000206002016000828254039250508190555083600260008973ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206004016000828254039250508190555083600260008873ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600401600082825401925050819055506001806000828254019250508190555060015492506080604051908101604052808273ffffffffffffffffffffffffffffffffffffffff1681526020018773ffffffffffffffffffffffffffffffffffffffff168152602001858152602001600115158152506003600085815260200190815260200160002060008201518160000160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060208201518160010160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506040820151816002015560608201518160030160006101000a81548160ff0219169083151502179055509050507f27f6bcd7b56fea6f94c7fa4ddf497e958429b88dbc2c3bc2c0ffb9080500b908600184604051808381526020018281526020019250505060405180910390a1610b61565b7f27f6bcd7b56fea6f94c7fa4ddf497e958429b88dbc2c3bc2c0ffb9080500b908600080604051808381526020018281526020019250505060405180910390a15b5050949350505050565b60036020528060005260406000206000915090508060000160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16908060010160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16908060020154908060030160009054906101000a900460ff16905084565b6000600260008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060050160019054906101000a900460ff161515610cae576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252600f8152602001807f696e76616c696420636f6d70616e79000000000000000000000000000000000081525060200191505060405180910390fd5b600260008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600301549050919050565b6002602052806000526040600020600091509050806000018054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610da45780601f10610d7957610100808354040283529160200191610da4565b820191906000526020600020905b815481529060010190602001808311610d8757829003601f168201915b5050505050908060010154908060020154908060030154908060040154908060050160009054906101000a900460ff16908060050160019054906101000a900460ff16905087565b6000600260008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060050160019054906101000a900460ff161515610eb2576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252600f8152602001807f696e76616c696420636f6d70616e79000000000000000000000000000000000081525060200191505060405180910390fd5b600260008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600401549050919050565b6000600260008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060050160019054906101000a900460ff161515610fc2576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252600f8152602001807f696e76616c696420636f6d70616e79000000000000000000000000000000000081525060200191505060405180910390fd5b600260008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600101549050919050565b60006001905060011515600260008773ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060050160019054906101000a900460ff161515141561107357600090505b60018114156111c55760e060405190810160405280858152602001848152602001838152602001600081526020016000815260200160001515815260200160011515815250600260008773ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600082015181600001908051906020019061111392919061215f565b506020820151816001015560408201518160020155606082015181600301556080820151816004015560a08201518160050160006101000a81548160ff02191690831515021790555060c08201518160050160016101000a81548160ff0219169083151502179055509050507f27f6bcd7b56fea6f94c7fa4ddf497e958429b88dbc2c3bc2c0ffb9080500b90860016000604051808381526020018281526020019250505060405180910390a1611206565b7f27f6bcd7b56fea6f94c7fa4ddf497e958429b88dbc2c3bc2c0ffb9080500b908600080604051808381526020018281526020019250505060405180910390a15b5050505050565b6000806001905060001515600260008673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060050160019054906101000a900460ff161515141561127557600090505b60001515600260008773ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060050160019054906101000a900460ff16151514156112d657600090505b60018114156114f85782600260008773ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206003016000828254019250508190555082600260008673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600401600082825401925050819055506001806000828254019250508190555060015491506080604051908101604052808673ffffffffffffffffffffffffffffffffffffffff1681526020018573ffffffffffffffffffffffffffffffffffffffff168152602001848152602001600115158152506003600084815260200190815260200160002060008201518160000160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060208201518160010160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506040820151816002015560608201518160030160006101000a81548160ff0219169083151502179055509050507f27f6bcd7b56fea6f94c7fa4ddf497e958429b88dbc2c3bc2c0ffb9080500b908600183604051808381526020018281526020019250505060405180910390a1611539565b7f27f6bcd7b56fea6f94c7fa4ddf497e958429b88dbc2c3bc2c0ffb9080500b908600080604051808381526020018281526020019250505060405180910390a15b509392505050565b6000806000806001915060001515600260008a73ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060050160019054906101000a900460ff16151514156115ac57600091505b60001515600260008973ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060050160019054906101000a900460ff161515141561160d57600091505b60015486111561161c57600091505b8773ffffffffffffffffffffffffffffffffffffffff166003600088815260200190815260200160002060010160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1614151561168c57600091505b84600360008881526020019081526020016000206002015410156116af57600091505b60018214156119d2576003600087815260200190815260200160002060000160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16905084600260008a73ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206004016000828254039250508190555084600260008973ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206004016000828254019250508190555084600360008881526020019081526020016000206002016000828254039250508190555084600260008973ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206001016000828254039250508190555084600260008a73ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600101600082825401925050819055506001806000828254019250508190555060015492506080604051908101604052808273ffffffffffffffffffffffffffffffffffffffff1681526020018873ffffffffffffffffffffffffffffffffffffffff168152602001868152602001600115158152506003600085815260200190815260200160002060008201518160000160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060208201518160010160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506040820151816002015560608201518160030160006101000a81548160ff021916908315150217905550905050600193507f27f6bcd7b56fea6f94c7fa4ddf497e958429b88dbc2c3bc2c0ffb9080500b908600184604051808381526020018281526020019250505060405180910390a1611a13565b7f27f6bcd7b56fea6f94c7fa4ddf497e958429b88dbc2c3bc2c0ffb9080500b908600080604051808381526020018281526020019250505060405180910390a15b505094509492505050565b60008060006001548411151515611a9d576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252600f8152602001807f696e76616c69642072656365697074000000000000000000000000000000000081525060200191505060405180910390fd5b6003600085815260200190815260200160002060010160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1692506003600085815260200190815260200160002060000160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff169150600360008581526020019081526020016000206002015490509193909250565b60006001905060011515600260008773ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060050160019054906101000a900460ff1615151415611b9657600090505b6001811415611ce8577f27f6bcd7b56fea6f94c7fa4ddf497e958429b88dbc2c3bc2c0ffb9080500b90860016000604051808381526020018281526020019250505060405180910390a160e060405190810160405280858152602001848152602001838152602001600081526020016000815260200160011515815260200160011515815250600260008773ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206000820151816000019080519060200190611c7792919061215f565b506020820151816001015560408201518160020155606082015181600301556080820151816004015560a08201518160050160006101000a81548160ff02191690831515021790555060c08201518160050160016101000a81548160ff021916908315150217905550905050611d29565b7f27f6bcd7b56fea6f94c7fa4ddf497e958429b88dbc2c3bc2c0ffb9080500b908600080604051808381526020018281526020019250505060405180910390a15b5050505050565b600080600191506003600084815260200190815260200160002060010160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1690506003600084815260200190815260200160002060000160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168473ffffffffffffffffffffffffffffffffffffffff16141515611de057600091505b6003600084815260200190815260200160002060020154600260008673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600101541015611e4557600091505b60001515600260008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060050160019054906101000a900460ff1615151415611ea657600091505b60001515600260008673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060050160019054906101000a900460ff1615151415611f0757600091505b600154831115611f1657600091505b6001821415612118576003600084815260200190815260200160002060020154600260008673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600101600082825403925050819055506003600084815260200190815260200160002060020154600260008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060010160008282540192505081905550600060036000858152602001908152602001600020600201819055506003600084815260200190815260200160002060020154600260008673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600301600082825403925050819055506003600084815260200190815260200160002060020154600260008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600401600082825403925050819055507f27f6bcd7b56fea6f94c7fa4ddf497e958429b88dbc2c3bc2c0ffb9080500b908600180604051808381526020018281526020019250505060405180910390a1612159565b7f27f6bcd7b56fea6f94c7fa4ddf497e958429b88dbc2c3bc2c0ffb9080500b908600080604051808381526020018281526020019250505060405180910390a15b50505050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106121a057805160ff19168380011785556121ce565b828001600101855582156121ce579182015b828111156121cd5782518255916020019190600101906121b2565b5b5090506121db91906121df565b5090565b61220191905b808211156121fd5760008160009055506001016121e5565b5090565b905600a165627a7a72305820707822b0506c88b4d8689ebddafaf3880c6af696a3ed8ac786c52329d78bc0a50029";

    public static final String ABI = "[{\"constant\":false,\"inputs\":[{\"name\":\"from\",\"type\":\"address\"},{\"name\":\"to\",\"type\":\"address\"},{\"name\":\"receiptId\",\"type\":\"uint256\"},{\"name\":\"amount\",\"type\":\"uint256\"}],\"name\":\"transfer\",\"outputs\":[{\"name\":\"id\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"name\":\"receipts\",\"outputs\":[{\"name\":\"from\",\"type\":\"address\"},{\"name\":\"to\",\"type\":\"address\"},{\"name\":\"amount\",\"type\":\"uint256\"},{\"name\":\"isValid\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"com\",\"type\":\"address\"}],\"name\":\"getToPay\",\"outputs\":[{\"name\":\"toPay\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"\",\"type\":\"address\"}],\"name\":\"companies\",\"outputs\":[{\"name\":\"name\",\"type\":\"string\"},{\"name\":\"balance\",\"type\":\"uint256\"},{\"name\":\"rate\",\"type\":\"uint256\"},{\"name\":\"toPay\",\"type\":\"uint256\"},{\"name\":\"toReceive\",\"type\":\"uint256\"},{\"name\":\"isBank\",\"type\":\"bool\"},{\"name\":\"isValid\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"com\",\"type\":\"address\"}],\"name\":\"getToReceive\",\"outputs\":[{\"name\":\"toReceive\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"com\",\"type\":\"address\"}],\"name\":\"getBanalance\",\"outputs\":[{\"name\":\"balance\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"com\",\"type\":\"address\"},{\"name\":\"name\",\"type\":\"string\"},{\"name\":\"balance\",\"type\":\"uint256\"},{\"name\":\"rate\",\"type\":\"uint256\"}],\"name\":\"newCompany\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"from\",\"type\":\"address\"},{\"name\":\"to\",\"type\":\"address\"},{\"name\":\"amount\",\"type\":\"uint256\"}],\"name\":\"deal\",\"outputs\":[{\"name\":\"id\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"to\",\"type\":\"address\"},{\"name\":\"bank\",\"type\":\"address\"},{\"name\":\"receiptId\",\"type\":\"uint256\"},{\"name\":\"amount\",\"type\":\"uint256\"}],\"name\":\"financing\",\"outputs\":[{\"name\":\"success\",\"type\":\"bool\"},{\"name\":\"id\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"receiptId\",\"type\":\"uint256\"}],\"name\":\"getReceipt\",\"outputs\":[{\"name\":\"to\",\"type\":\"address\"},{\"name\":\"from\",\"type\":\"address\"},{\"name\":\"amount\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"bank\",\"type\":\"address\"},{\"name\":\"name\",\"type\":\"string\"},{\"name\":\"balance\",\"type\":\"uint256\"},{\"name\":\"rate\",\"type\":\"uint256\"}],\"name\":\"newBank\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"from\",\"type\":\"address\"},{\"name\":\"receiptId\",\"type\":\"uint256\"}],\"name\":\"pay\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[{\"name\":\"name\",\"type\":\"string\"},{\"name\":\"balance\",\"type\":\"uint256\"},{\"name\":\"rate\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"constructor\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"ret\",\"type\":\"int256\"},{\"indexed\":false,\"name\":\"receiptId\",\"type\":\"uint256\"}],\"name\":\"resultEvent\",\"type\":\"event\"}]";

    public static final TransactionDecoder transactionDecoder = new TransactionDecoder(ABI, BINARY);

    public static final String FUNC_TRANSFER = "transfer";

    public static final String FUNC_RECEIPTS = "receipts";

    public static final String FUNC_GETTOPAY = "getToPay";

    public static final String FUNC_COMPANIES = "companies";

    public static final String FUNC_GETTORECEIVE = "getToReceive";

    public static final String FUNC_GETBANALANCE = "getBanalance";

    public static final String FUNC_NEWCOMPANY = "newCompany";

    public static final String FUNC_DEAL = "deal";

    public static final String FUNC_FINANCING = "financing";

    public static final String FUNC_GETRECEIPT = "getReceipt";

    public static final String FUNC_NEWBANK = "newBank";

    public static final String FUNC_PAY = "pay";

    public static final Event RESULTEVENT_EVENT = new Event("resultEvent", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}, new TypeReference<Uint256>() {}));
    ;

    @Deprecated
    protected AR(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected AR(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected AR(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected AR(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static TransactionDecoder getTransactionDecoder() {
        return transactionDecoder;
    }

    public RemoteCall<TransactionReceipt> transfer(String from, String to, BigInteger receiptId, BigInteger amount) {
        final Function function = new Function(
                FUNC_TRANSFER, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(from), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(to), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(receiptId), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void transfer(String from, String to, BigInteger receiptId, BigInteger amount, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_TRANSFER, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(from), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(to), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(receiptId), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String transferSeq(String from, String to, BigInteger receiptId, BigInteger amount) {
        final Function function = new Function(
                FUNC_TRANSFER, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(from), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(to), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(receiptId), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public Tuple4<String, String, BigInteger, BigInteger> getTransferInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_TRANSFER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple4<String, String, BigInteger, BigInteger>(

                (String) results.get(0).getValue(), 
                (String) results.get(1).getValue(), 
                (BigInteger) results.get(2).getValue(), 
                (BigInteger) results.get(3).getValue()
                );
    }

    public Tuple1<BigInteger> getTransferOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_TRANSFER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple1<BigInteger>(

                (BigInteger) results.get(0).getValue()
                );
    }

    public RemoteCall<Tuple4<String, String, BigInteger, Boolean>> receipts(BigInteger param0) {
        final Function function = new Function(FUNC_RECEIPTS, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<Bool>() {}));
        return new RemoteCall<Tuple4<String, String, BigInteger, Boolean>>(
                new Callable<Tuple4<String, String, BigInteger, Boolean>>() {
                    @Override
                    public Tuple4<String, String, BigInteger, Boolean> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple4<String, String, BigInteger, Boolean>(
                                (String) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue(), 
                                (Boolean) results.get(3).getValue());
                    }
                });
    }

    public RemoteCall<BigInteger> getToPay(String com) {
        final Function function = new Function(FUNC_GETTOPAY, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(com)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<Tuple7<String, BigInteger, BigInteger, BigInteger, BigInteger, Boolean, Boolean>> companies(String param0) {
        final Function function = new Function(FUNC_COMPANIES, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Bool>() {}, new TypeReference<Bool>() {}));
        return new RemoteCall<Tuple7<String, BigInteger, BigInteger, BigInteger, BigInteger, Boolean, Boolean>>(
                new Callable<Tuple7<String, BigInteger, BigInteger, BigInteger, BigInteger, Boolean, Boolean>>() {
                    @Override
                    public Tuple7<String, BigInteger, BigInteger, BigInteger, BigInteger, Boolean, Boolean> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple7<String, BigInteger, BigInteger, BigInteger, BigInteger, Boolean, Boolean>(
                                (String) results.get(0).getValue(), 
                                (BigInteger) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue(), 
                                (BigInteger) results.get(3).getValue(), 
                                (BigInteger) results.get(4).getValue(), 
                                (Boolean) results.get(5).getValue(), 
                                (Boolean) results.get(6).getValue());
                    }
                });
    }

    public RemoteCall<BigInteger> getToReceive(String com) {
        final Function function = new Function(FUNC_GETTORECEIVE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(com)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> getBanalance(String com) {
        final Function function = new Function(FUNC_GETBANALANCE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(com)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> newCompany(String com, String name, BigInteger balance, BigInteger rate) {
        final Function function = new Function(
                FUNC_NEWCOMPANY, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(com), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(name), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(balance), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(rate)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void newCompany(String com, String name, BigInteger balance, BigInteger rate, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_NEWCOMPANY, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(com), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(name), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(balance), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(rate)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String newCompanySeq(String com, String name, BigInteger balance, BigInteger rate) {
        final Function function = new Function(
                FUNC_NEWCOMPANY, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(com), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(name), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(balance), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(rate)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public Tuple4<String, String, BigInteger, BigInteger> getNewCompanyInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_NEWCOMPANY, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple4<String, String, BigInteger, BigInteger>(

                (String) results.get(0).getValue(), 
                (String) results.get(1).getValue(), 
                (BigInteger) results.get(2).getValue(), 
                (BigInteger) results.get(3).getValue()
                );
    }

    public RemoteCall<TransactionReceipt> deal(String from, String to, BigInteger amount) {
        final Function function = new Function(
                FUNC_DEAL, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(from), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(to), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void deal(String from, String to, BigInteger amount, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_DEAL, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(from), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(to), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String dealSeq(String from, String to, BigInteger amount) {
        final Function function = new Function(
                FUNC_DEAL, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(from), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(to), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public Tuple3<String, String, BigInteger> getDealInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_DEAL, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple3<String, String, BigInteger>(

                (String) results.get(0).getValue(), 
                (String) results.get(1).getValue(), 
                (BigInteger) results.get(2).getValue()
                );
    }

    public Tuple1<BigInteger> getDealOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_DEAL, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple1<BigInteger>(

                (BigInteger) results.get(0).getValue()
                );
    }

    public RemoteCall<TransactionReceipt> financing(String to, String bank, BigInteger receiptId, BigInteger amount) {
        final Function function = new Function(
                FUNC_FINANCING, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(to), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(bank), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(receiptId), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void financing(String to, String bank, BigInteger receiptId, BigInteger amount, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_FINANCING, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(to), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(bank), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(receiptId), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String financingSeq(String to, String bank, BigInteger receiptId, BigInteger amount) {
        final Function function = new Function(
                FUNC_FINANCING, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(to), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(bank), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(receiptId), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public Tuple4<String, String, BigInteger, BigInteger> getFinancingInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_FINANCING, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple4<String, String, BigInteger, BigInteger>(

                (String) results.get(0).getValue(), 
                (String) results.get(1).getValue(), 
                (BigInteger) results.get(2).getValue(), 
                (BigInteger) results.get(3).getValue()
                );
    }

    public Tuple2<Boolean, BigInteger> getFinancingOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_FINANCING, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}, new TypeReference<Uint256>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple2<Boolean, BigInteger>(

                (Boolean) results.get(0).getValue(), 
                (BigInteger) results.get(1).getValue()
                );
    }

    public RemoteCall<Tuple3<String, String, BigInteger>> getReceipt(BigInteger receiptId) {
        final Function function = new Function(FUNC_GETRECEIPT, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(receiptId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
        return new RemoteCall<Tuple3<String, String, BigInteger>>(
                new Callable<Tuple3<String, String, BigInteger>>() {
                    @Override
                    public Tuple3<String, String, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple3<String, String, BigInteger>(
                                (String) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue());
                    }
                });
    }

    public RemoteCall<TransactionReceipt> newBank(String bank, String name, BigInteger balance, BigInteger rate) {
        final Function function = new Function(
                FUNC_NEWBANK, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(bank), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(name), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(balance), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(rate)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void newBank(String bank, String name, BigInteger balance, BigInteger rate, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_NEWBANK, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(bank), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(name), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(balance), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(rate)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String newBankSeq(String bank, String name, BigInteger balance, BigInteger rate) {
        final Function function = new Function(
                FUNC_NEWBANK, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(bank), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(name), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(balance), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(rate)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public Tuple4<String, String, BigInteger, BigInteger> getNewBankInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_NEWBANK, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple4<String, String, BigInteger, BigInteger>(

                (String) results.get(0).getValue(), 
                (String) results.get(1).getValue(), 
                (BigInteger) results.get(2).getValue(), 
                (BigInteger) results.get(3).getValue()
                );
    }

    public RemoteCall<TransactionReceipt> pay(String from, BigInteger receiptId) {
        final Function function = new Function(
                FUNC_PAY, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(from), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(receiptId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void pay(String from, BigInteger receiptId, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_PAY, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(from), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(receiptId)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String paySeq(String from, BigInteger receiptId) {
        final Function function = new Function(
                FUNC_PAY, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(from), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(receiptId)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public Tuple2<String, BigInteger> getPayInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_PAY, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple2<String, BigInteger>(

                (String) results.get(0).getValue(), 
                (BigInteger) results.get(1).getValue()
                );
    }

    public List<ResultEventEventResponse> getResultEventEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(RESULTEVENT_EVENT, transactionReceipt);
        ArrayList<ResultEventEventResponse> responses = new ArrayList<ResultEventEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ResultEventEventResponse typedResponse = new ResultEventEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.ret = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.receiptId = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public void registerresultEventEventLogFilter(String fromBlock, String toBlock, List<String> otherTopcs, EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(RESULTEVENT_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,fromBlock,toBlock,otherTopcs,callback);
    }

    public void registerresultEventEventLogFilter(EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(RESULTEVENT_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,callback);
    }

    @Deprecated
    public static AR load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new AR(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static AR load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new AR(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static AR load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new AR(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static AR load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new AR(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<AR> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, String name, BigInteger balance, BigInteger rate) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(name), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(balance), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(rate)));
        return deployRemoteCall(AR.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<AR> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, String name, BigInteger balance, BigInteger rate) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(name), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(balance), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(rate)));
        return deployRemoteCall(AR.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<AR> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String name, BigInteger balance, BigInteger rate) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(name), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(balance), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(rate)));
        return deployRemoteCall(AR.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<AR> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String name, BigInteger balance, BigInteger rate) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(name), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(balance), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(rate)));
        return deployRemoteCall(AR.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static class ResultEventEventResponse {
        public Log log;

        public BigInteger ret;

        public BigInteger receiptId;
    }
}
