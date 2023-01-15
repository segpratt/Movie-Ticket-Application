import * as React from 'react';
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import Container from '@mui/material/Container'
import Paper from '@mui/material/Paper'
import Button from '@mui/material/Button'
import { useState, useEffect } from 'react';
import { Link, useLinkClickHandler } from 'react-router-dom';
export var theatreSelected = undefined;

export default function Theatres() {
  const paperStyle = {padding:'50px 20px', width:600, margin:'20px auto'}
  const [theatres,setTheatres] = useState([])

  useEffect(()=>{
    fetch("http://localhost:8080/api/v1/theatres")
    .then(res=>res.json())
    .then(result=>{
      setTheatres(result);
    })
  },[])

  const clickHandle = (i) =>{
    theatreSelected = theatres[i];
    console.log(theatreSelected)
  }

  return (
    <Container>
      <Paper elevation={3} style={paperStyle}>
      <h1>Theatres</h1>
        {theatres.map((theatre, i)=>( 
        <Link to={'/movie-ticket-system/movies'} onClick={() => clickHandle(i)} key={i}>
          <Paper elevation={6} style={{margin:"10px",padding:"15px",textAlign:"left"}} >
            ID:{theatre.tId},
            Name:{theatre.name}
          </Paper>
        </Link>
        ))}
      </Paper>
     
    </Container>
  );
}
