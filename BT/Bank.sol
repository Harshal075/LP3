// SPDX-License-Identifier: MIT
pragma solidity ^0.8.0;

contract Banking {
    mapping(address => uint) public userAccount;
    mapping(address => bool) public userExists;

    function createAcc() public payable returns (string memory) {
        require(!userExists[msg.sender], "Account already created");
        
        userAccount[msg.sender] = msg.value;
        userExists[msg.sender] = true;
        
        return "Account created";
    }
   
    function deposit() public payable returns (string memory) {
        require(userExists[msg.sender], "Account is not created");
        require(msg.value > 0, "Deposit value must be greater than zero");
        
        userAccount[msg.sender] += msg.value;
        return "Deposit successful";
    }
   
    function withdraw(uint amount) public returns (string memory) {
        require(userExists[msg.sender], "Account is not created");
        require(userAccount[msg.sender] >= amount, "Insufficient balance");
        require(amount > 0, "Withdrawal amount must be greater than zero");
        
        address payable senderAddress = payable(msg.sender);
        senderAddress.transfer(amount);
        
        userAccount[msg.sender] -= amount;
        
        return "Withdrawal successful";
    }
  
    function transferAmount(address payable recipient, uint amount) public returns (string memory) {
        require(userExists[msg.sender], "Account is not created");
        require(userExists[recipient], "Recipient account does not exist");
        require(userAccount[msg.sender] >= amount, "Insufficient balance");
        require(amount > 0, "Transfer amount must be greater than zero");
        
        userAccount[msg.sender] -= amount;
        userAccount[recipient] += amount;
        
        return "Transfer successful";
    }
   
    function sendAmount(address payable toAddress, uint256 amount) public returns (string memory) {
        require(userExists[msg.sender], "Account is not created");
        require(userAccount[msg.sender] >= amount, "Insufficient balance");
        require(amount > 0, "Transfer amount must be greater than zero");
        
        userAccount[msg.sender] -= amount;
        toAddress.transfer(amount);
        
        return "Transfer successful";
    }
   
    function getUserAccountBalance() public view returns (uint) {
        return userAccount[msg.sender];
    }
   
    function isAccountExist() public view returns (bool) {
        return userExists[msg.sender];
    }
}
