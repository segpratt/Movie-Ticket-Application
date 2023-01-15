import * as React from 'react';
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import Container from '@mui/material/Container'
import Paper from '@mui/material/Paper'
import Button from '@mui/material/Button'
import { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import { theatreSelected } from "./Theatres.js";

export var movieSelected = undefined;

export default function Movies() {
  const paperStyle = {padding:'50px 20px', width:600, margin:'20px auto'}
  const [movies,setMovies] = useState([])
  const [tId,settId] = useState('')

  useEffect(()=>{
    fetch( "http://localhost:8080/api/v1/theatres/"+theatreSelected.tId+"/movies/")  
    .then(res=>res.json())
    .then(result=>{
      setMovies(sortByKey(JSON.parse(JSON.stringify(result)), "mId"));
    })
  },[])

  const clickHandle = (i) =>{
    movieSelected = movies[i];
    console.log(movieSelected)
  }

  function sortByKey(array, key) {
		return array.sort(function (a, b) {
			var x = Number(a[key]);
			var y = Number(b[key]);
			return x < y ? -1 : x > y ? 1 : 0;
		});
	}

  return (
    <Container>
      <h1><Link className="link" to='/movie-ticket-system/searchmovie'>Search Movie</Link></h1>
      <Paper elevation={3} style={paperStyle}>
      <h1>Movies</h1>
      <p>Theatre:{theatreSelected.name}</p>
        {movies.map((movie,i) =>(
        <Link to={'/movie-ticket-system/showtimes'} onClick={() => clickHandle(i)} key={movie.mId}>
          <Paper elevation={6} style={{margin:"10px",padding:"15px",textAlign:"left"}} >
            ID:{movie.mId},
            Name:{movie.name} <br></br>
            Release Date:{movie.releaseDate}
          </Paper>
        </Link>
        ))}
      </Paper>
     
    </Container>
  );
}
