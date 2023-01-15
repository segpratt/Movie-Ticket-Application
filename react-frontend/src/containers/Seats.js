import * as React from 'react';
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import Container from '@mui/material/Container'
import Paper from '@mui/material/Paper'
import Button from '@mui/material/Button'
import { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import { useNavigate } from "react-router-dom";
import { theatreSelected } from "./Theatres.js";
import { movieSelected } from "./Movies.js";
import { stSelected } from "./Showtimes.js";
import { userInfo } from "./Login.js";
import ListItemButton from '@mui/material/ListItemButton';
import ListItemIcon from '@mui/material/ListItemIcon';
import Checkbox from '@mui/material/Checkbox';
import Typography from '@mui/material/Typography';
export var paymentInfo = undefined;

export default function Seats() {
  const nav = useNavigate();
  const paperStyle = {padding:'50px 20px', width:600, margin:'20px auto'}
  const [seats,setSeats] = useState([])
  const [createdTicket,setTicket] = useState(undefined)
  const [createdSeat,setSeat] = useState(undefined)
  const [cardNo,setCardNo] = useState('')
  const [expiry,setExpiry] = useState('')
  const [cvv,setCvv] = useState('')
  const [name,setName] = useState('')

  const [isError, setIsError] = useState(false);
  const [isSubmitted, setIsSubmitted] = useState(false);
  const [isSubmitted2, setIsSubmitted2] = useState(false);
  const [isSubmitted3, setIsSubmitted3] = useState(false);
  const [checked, setChecked] = useState([]);
  

  function sortByKey(array, key) {
		return array.sort(function (a, b) {
			var x = Number(a[key]);
			var y = Number(b[key]);
			return x < y ? -1 : x > y ? 1 : 0;
		});
	}

  // load seats
  useEffect(()=>{
    console.log(stSelected.sId)
    fetch("http://localhost:8080/api/v1/theatres/"+stSelected.sId+"/seats")
    .then(res=>res.json())
    .then(result=>{
      setSeats(sortByKey(JSON.parse(JSON.stringify(result)), "id"));
    })
  },[])

  // add ticket to user
  useEffect(()=>{
    console.log(createdTicket)
    if (createdTicket !== undefined){
      fetch("http://localhost:8080/api/v1/registereduser/"+createdTicket.id+"/add/"+userInfo.email, {
        method: "PUT",
        headers: { "Content-Type": "application/json" }
      })
        .then(() => {
          console.log("Ticket Added to User");
          setIsSubmitted(true);
          setIsError(false);
          fetch("http://localhost:8080/api/v1/theatres/"+stSelected.sId+"/seats")
            .then(res=>res.json())
            .then(result=>{
              setSeats(sortByKey(JSON.parse(JSON.stringify(result)), "id"));
            })
        })
        .catch(() => {
          console.log("err2");
          setIsError(true);
          setIsSubmitted(false);
        })
    }
  },[createdTicket]);

  // get last created ticket
  useEffect(()=>{
      fetch("http://localhost:8080/api/v1/ticket/all")
      .then(res=>res.json())
      .then(result=>{
          setTicket(result[result.length-1])
          setIsSubmitted2(false)
      })
  },[isSubmitted2]);

  const handleSubmit = async (e) => {
    const id = [];
    let i=0;
    for (i; i < checked.length; i++) {
      id.push(seats[checked[i]].id);
    }
    console.log(id)

    i=0;
    for (i; i < id.length; i++) {
      var seatSelectedName = undefined;
      var seatSelectedId = undefined;
      for (var j = 0; j < seats.length; j++) {
        if (seats[j].id === id[i]) {
          seatSelectedName = seats[j].letter_row + seats[j].number_row;
          seatSelectedId = seats[j].id;
          const ticket={
            theatre: theatreSelected.name, 
            movie: movieSelected.name, 
            showtime: stSelected.showtime, 
            seatDesc: seatSelectedName, 
            seat: seatSelectedId,
            price: 10.00}
          console.log(ticket)
          // create ticket
          await fetch("http://localhost:8080/api/v1/ticket/add", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(ticket)
              })
                .then(() => {
                  console.log(ticket);
                  console.log("Ticket Created");
                  setIsSubmitted2(true);
                  setIsError(false);
                })
                .catch(() => {
                  console.log("err1");
                  setIsError(true);
                  setIsSubmitted(false);
                });
      
          // change seat status to occupied
          await fetch("http://localhost:8080/api/v1/seat/reserveSeat/"+seatSelectedId, {
            method: "PUT",
            headers: { "Content-Type": "application/json" }
          })
            .then(() => {
              console.log("Seat is now occupied");
              setIsSubmitted(true);
              setIsError(false);
            })
            .catch(() => {
              console.log("err");
              setIsError(true);
              setIsSubmitted(false);
            })
          }
        }
      }
  };

  const handleProceed = async () =>{
    const creditCard = {cardNo, expiry, cvv, name}
      paymentInfo = creditCard
      console.log(creditCard)
      console.log(userInfo.cardNo)
    // TODO: send data to database
    await fetch(`http://localhost:8080/api/v1/payment/addPayment/${userInfo.id}/${checked.length*10}`,
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
    nav('/movie-ticket-system/payment')
  }

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
      <h1>Seats</h1>
      <p>
        Theatre:{theatreSelected.name} <br></br>
        Movie:{movieSelected.name} <br></br>
        Showtime:{stSelected.showtime} <br></br>
        Total Price:${checked.length*10}
      </p>
      <p align="center">
        ___Screen___ <br></br>
        A1, A2, A3, A4 <br></br>
        B1, B2, B3, B4 <br></br>
        C1, C2, C3, C4 <br></br>
        D1, D2, D3, D4 <br></br>
      </p>
          <Box textAlign='center'>
            <Button variant="contained" onClick={handleProceed}>
              Pay
            </Button>
          </Box>
          <br></br>
          <Box textAlign='center'>
            <Button variant="contained" onClick={handleSubmit} fullWidth>
              Add to Cart
            </Button>
          </Box>
        {seats.map((seat,i)=>(
          <div key={seat.id}>
          {seat.reserved ? (
                <Paper elevation={6} style={{margin:"10px",padding:"15px",textAlign:"left"}} >
                  ID:{seat.id},
                  Seat:{seat.letter_row+seat.number_row} <br></br>
                  <Typography color="error.main">
                  Status:{seat.reserved ? 'Occupied' : 'Empty'}
                  </Typography>
                </Paper>
          ) : (
              <Paper elevation={6} style={{margin:"10px",padding:"15px",textAlign:"left"}}>
              <ListItemButton role={undefined} onClick={handleToggle(i)} dense>
                <ListItemIcon>
                  <Checkbox
                    edge="start"
                    checked={checked.indexOf(i) !== -1}
                    tabIndex={-1}
                    disableRipple
                  />
                </ListItemIcon>
                  ID:{seat.id},
                  Seat:{seat.letter_row+seat.number_row} <br></br>
                  Status:{seat.reserved ? 'Occupied' : 'Empty'}
                </ListItemButton>
              </Paper>
          )}
          </div>
        ))}
          
      </Paper>
     
    </Container>
  );
}
