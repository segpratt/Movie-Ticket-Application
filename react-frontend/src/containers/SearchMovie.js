import * as React from "react";
import Box from "@mui/material/Box";
import TextField from "@mui/material/TextField";
import Container from "@mui/material/Container";
import Paper from "@mui/material/Paper";
import Button from "@mui/material/Button";
import { useState, useEffect } from "react";
import { theatreSelected } from "./Theatres.js";

export default function SearchMovie() {
	const paperStyle = {
		padding: "50px 20px",
		width: 600,
		margin: "20px auto",
	};
	const [isError, setIsError] = useState(false);
	// const [isSubmitted, setIsSubmitted] = useState(false);
	const [id, setId] = useState("");
	const [movie, setMovie] = useState();
	const [courses, setCourses] = useState();

	const handleClick = (e) => {
		e.preventDefault();
		const s = { id };
		// console.log(s);
		console.log(id);
		// TODO: send data to database
		fetch("http://localhost:8080/api/v1/theatres/"+theatreSelected.tId+"/movies/"+id, {
			method: "GET",
			headers: { "Content-Type": "application/json" },
		})
			.then((response) => {
				return response.json();
			})
			.then((data) => {
				// setIsSubmitted(true);
				// setCourses(null);
				setIsError(false);
				setMovie(data);
			})
			.catch(() => {
				console.log("Error");
				setIsError(true);
				// setIsSubmitted(false);
			});
	};

	return (
		<Container>
			<Paper elevation={3} style={paperStyle}>
				<h1>Search Movie</h1>
				<p>Search a movie from the database</p>

				<Box
					component="form"
					sx={{
						"& > :not(style)": {
							m: 1,
							width: 500,
							maxWidth: "100%",
						},
					}}
					noValidate
					autoComplete="off"
				>
					<TextField
						required
						id="outlined-required"
						label="Movie Name"
						variant="outlined"
						fullWidth
						value={id}
						onChange={(e) => setId(e.target.value)}
					/>

					<Button variant="contained" onClick={handleClick}>
						Search
					</Button>
				</Box>
			</Paper>
			<Paper elevation={3} style={paperStyle}>
				<h1>Response</h1>
				{movie ? (
					<div>
						ID:{movie.mId},
            			Name:{movie.name} <br></br>
            			Release Date:{movie.releaseDate}
					</div>
				) : (
					<div></div>
				)}
				{isError ? <div>Error. Please try again.</div> : <div></div>}
			</Paper>
		</Container>
	);
}
