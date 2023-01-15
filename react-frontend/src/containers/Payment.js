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
var totalPrice = 0.00;
export var paymentInfo = undefined;


export default function Payment() {
  const paperStyle = {padding:'50px 20px', width:600, margin:'20px auto'}
  const [isError, setIsError] = useState(false);
  const [isSubmitted, setIsSubmitted] = useState(false);
  const [isSubmitted2, setIsSubmitted2] = useState(false);
  const [isCancelled, setIsCancelled] = useState(false);
  const [isGuest, setIsGuest] = useState(false);
  const [cardNo,setCardNo] = useState('')
  const [expiry,setExpiry] = useState('')
  const [cvv,setCvv] = useState('')
  const [name,setName] = useState('')
  const [price,setPrice] = useState('')
  const [balance,setBalance] = useState([])

  const [tickets,setTickets] = useState([])
  const [checked, setChecked] = useState([]);

  function sortByKey(array, key) {
		return array.sort(function (a, b) {
			var x = Number(a[key]);
			var y = Number(b[key]);
			return x < y ? -1 : x > y ? 1 : 0;
		});
	}

  useEffect(()=>{
    if (userInfo.id === 3) {
        setIsGuest(true)
    }
    fetch("http://localhost:8080/api/v1/registereduser/tickets"+userInfo.email)
    .then(res=>res.json())
    .then(result=>{
      setTickets(sortByKey(JSON.parse(JSON.stringify(result)), "id"));
    })
  },[])

  useEffect(()=>{
    totalPrice = tickets.length * 10;
    setPrice(totalPrice);
    console.log(tickets.length);
  },[tickets])

  useEffect(()=>{
		if (isSubmitted2 === true) {
			userInfo.accountBalance = balance;
			setIsSubmitted2(false);
		}
	  },[isSubmitted2])

  const handleClick=(e)=>{
    e.preventDefault()

    const creditCard = {cardNo, expiry, cvv, name}
      paymentInfo = creditCard
      console.log(creditCard)
      console.log(userInfo.cardNo)
    // TODO: send data to database
    fetch(`http://localhost:8080/api/v1/payment/addPayment/${userInfo.id}/${price}`,
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

  const handleDelete = (e) => {
    e.preventDefault()
    const id = [];
    var userId = userInfo.id;
    for (var i = 0; i < checked.length; i++) {
      id.push(tickets[checked[i]].id);
    }
    console.log(id)
    var refundAmount = refundAmount = id.length * 10;
		if(userId === 3) {
			refundAmount = refundAmount * 0.85;
		}

    for (var i = 0; i < id.length; i++) {
    fetch("http://localhost:8080/api/v1/ticket/delete/"+id[i], {
        method: "DELETE",
        headers: { "Content-Type": "application/json" }
      })
        .then(() => {
          console.log("Ticket Deleted From User");
          setIsCancelled(true)
          setIsSubmitted(true);
          setIsError(false);

          console.log("getting user info")
					fetch(`http://localhost:8080/api/v1/registereduser/getUser/${userInfo.id}`)
					.then(res=>res.json())
					.then(result=>{
						setBalance(result)
						setIsSubmitted2(true)
						console.log(result)
					})

					fetch("http://localhost:8080/api/v1/registereduser/tickets"+userInfo.email)
					.then(res=>res.json())
					.then(result=>{
						setTickets(sortByKey(JSON.parse(JSON.stringify(result)), "id"));
						setChecked([]);
					})
        })
        .catch(() => {
          console.log("err2");
          setIsCancelled(false)
          setIsError(true);
          setIsSubmitted(false);
        })
      }

      fetch(`http://localhost:8080/api/v1/payment/addRefundPayment/${refundAmount}/${userInfo.id}`,
		{
		method:"PUT"
		}).then(()=>{
			console.log("Refund Successful")
		})

  };

  const handleToggle = (value) => () => {
    const currentIndex = checked.indexOf(value);
    const newChecked = [...checked];
    if (currentIndex === -1) {
      newChecked.push(value);
    } else {
      newChecked.splice(currentIndex, 1);
    }
    setChecked(newChecked);
  };

  return (
    <Container>

      <Paper elevation={3} style={paperStyle}>
        <h1>Congratulations!</h1>
        <p>You paid ${totalPrice}<br></br>Please check your email</p>

    
      <Box
        component="form"
        sx={{
          '& > :not(style)': { m: 1, width: 500, maxWidth: '100%' },
        }}
        noValidate
        autoComplete="off"
      >
        {/* {isGuest ? (
        <>
        <TextField required id="outlined-required" label="Credit Card Number" variant="outlined" fullWidth
        value = {cardNo}
        onChange={(e)=>setCardNo(e.target.value)}
        />
        <TextField required id="outlined-required" label="Expiry Date (MMYY)" variant="outlined" fullWidth
        value = {expiry}
        onChange={(e)=>setExpiry(e.target.value)}
        />
        <TextField required id="outlined-required" label="CVV" variant="outlined" fullWidth
        value = {cvv}
        onChange={(e)=>setCvv(e.target.value)}
        />
        <TextField required id="outlined-required" label="Cardholder Name" variant="outlined" fullWidth
        value = {name}
        onChange={(e)=>setName(e.target.value)}
        />
        </>) : (
          ''
        )} */}
        
        {/* <Button variant="contained" onClick={handleClick}>
          Pay
        </Button> */}
      </Box>
    </Paper>

      

      <Paper elevation={3} style={paperStyle}>
      <h1>Tickets</h1>
        {tickets.map((ticket,i) =>( 
          <ListItemButton role={undefined} onClick={handleToggle(i)} key={ticket.id} dense>
            <ListItemIcon>
                <Checkbox
                  edge="start"
                  checked={checked.indexOf(i) !== -1}
                  tabIndex={-1}
                  disableRipple
                />
              </ListItemIcon>
            <Paper elevation={6} style={{margin:"10px",padding:"15px",textAlign:"left"}}>
              ID:{ticket.id} <br></br>
              Theatre:{ticket.theatre} <br></br>
              Movie:{ticket.movie} <br></br>
              Showtime:{ticket.showtime} <br></br>
              Seat:{ticket.seatDesc}
            </Paper>
          </ListItemButton>
          ))}
          <br></br>
          <Box textAlign='center'>
            <Button variant="contained" onClick={handleDelete}>
              Delete
            </Button>
          </Box>
      </Paper>

      <Paper elevation={3} style={paperStyle}>
        <h1>Response</h1>
        {isSubmitted ? <div>Cancellation Successful! Please check your email.</div> : 
                        <div></div>}
        {isError ? <div>Error. Please try again.</div> : 
                        <div></div>}
      </Paper>
    </Container>
  );
}
