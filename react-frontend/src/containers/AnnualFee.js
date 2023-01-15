import * as React from 'react';
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import Container from '@mui/material/Container'
import Paper from '@mui/material/Paper'
import Button from '@mui/material/Button'
import { useState, useEffect } from 'react';
import { userInfo } from "./Login.js";
import ListItemButton from '@mui/material/ListItemButton';
import ListItemIcon from '@mui/material/ListItemIcon';
import Checkbox from '@mui/material/Checkbox';
import { useNavigate } from "react-router-dom";
import { useAppContext } from "../lib/contextLib";
var totalPrice = 20.00;
export var paymentInfo = undefined;


export default function AnnualFee() {
  const nav = useNavigate();
  const paperStyle = {padding:'50px 20px', width:600, margin:'20px auto'}
  const [isError, setIsError] = useState(false);
  const [isSubmitted, setIsSubmitted] = useState(false);
  const [cardNo,setCardNo] = useState('')
  const [expiry,setExpiry] = useState('')
  const [cvv,setCvv] = useState('')
  const [name,setName] = useState('')
  const [price,setPrice] = useState('')

  const { notPaid } = useAppContext();

  useEffect(()=>{
    setPrice(totalPrice)
  },[])

  function sortByKey(array, key) {
		return array.sort(function (a, b) {
			var x = Number(a[key]);
			var y = Number(b[key]);
			return x < y ? -1 : x > y ? 1 : 0;
		});
	}

  const handleClick=async (e)=>{
    e.preventDefault()
    
    const creditCard = {cardNo, expiry, cvv, name}
      paymentInfo = creditCard
      console.log(creditCard)
      console.log(userInfo.cardNo)
    // TODO: send data to database
    await fetch("http://localhost:8080/api/v1/registereduser/"+userInfo.id+"/setDate", {
			method: "PUT",
			headers: { "Content-Type": "application/json" },
		})
			.then(() => {
				console.log("Date Updated");
        notPaid(false);
        fetch("http://localhost:8080/api/v1/registereduser/"+userInfo.email, {
          method: "GET",
          headers: { "Content-Type": "application/json" },
          })
            .then((response) => {
              return response.json();
            })
            .then((data) => {
              console.log("Data")
              console.log(data)
              userInfo.dateRegistered = data.dateRegistered;
            })
            .catch(() => {
              console.log("Error");
            });
			})
			.catch(() => {
				console.log("Error");
			});

    await fetch(`http://localhost:8080/api/v1/payment/addPayment/${userInfo.id}/${price}`,
        {
          method:"PUT",
          headers:{"Content-Type":"application/json"},
          body:JSON.stringify(creditCard)
        }).then(()=>{
      console.log("Payment Successful")
      setIsSubmitted(true);
      setIsError(false);
      
      console.log("getting user info")
      fetch(`http://localhost:8080/api/v1/registereduser/getUser/${userInfo.id}`)
      .then(res=>res.json())
      .then(result=>{
        console.log(result)
        userInfo.accountBalance = result;
        nav("/movie-ticket-system/theatres");
      })
    })
    .catch(() => {
      console.log("err2");
      setIsError(true);
      setIsSubmitted(false);
    }).catch(()=>{
      console.log("Error")
      setIsError(true);
      setIsSubmitted(false);
    })
}

  return (
    <Container>
      <Paper elevation={3} style={paperStyle}>
        <h1>Annual Fee Payment</h1>
        <p>Your total is ${totalPrice}</p>

    <Box
      component="form"
      sx={{
        '& > :not(style)': { m: 1, width: 500, maxWidth: '100%' },
      }}
      noValidate
      autoComplete="off"
    >
      
      <Button variant="contained" onClick={handleClick}>
        Pay
      </Button>
    </Box>
    </Paper>

      {/* <Paper elevation={3} style={paperStyle}>
        <h1>Response</h1>
        {isSubmitted ? <div>Payment Successful! Please check your email.</div> : 
                        <div></div>}
        {isError ? <div>Error. Please try again.</div> : 
                        <div></div>}
      </Paper> */}
    </Container>
  );
}
