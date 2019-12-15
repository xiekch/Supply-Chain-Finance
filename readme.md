# Supply Chain Finance

基于已有的开源区块链系统[FISCO-BCOS](https://github.com/FISCO-BCOS/FISCO-BCOS)，以联盟链为主，开发的基于区块链或区块链智能合约的供应链金融平台，实现供应链应收账款资产的溯源、流转。

## 合约

[AR.sol](./contracts/solidity/AR.sol)



## 服务器

[server](./server/)



## 链端

[nodes](./nodes/)



## 部署

下载项目

```bash
git clone https://github.com/xiekch/Supply-Chain-Finance
```

进入目录

```bash
cd Supply-Chain-Finance
```

先运行fisco-bcos链端

```
./nodes/127.0.0.1/start_all.sh 
```

再运行服务器

```
cd server
./gradlew build
java -jar ./build/libs/server-0.0.1-SNAPSHOT.jar
```

打开浏览器，打开 http://localhost:8080

## 报告

[report](./report/report.md)

## 演示视频

[presentation](./report/presentation.mp4)