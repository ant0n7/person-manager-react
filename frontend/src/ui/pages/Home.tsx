import ReactDOM from 'react-dom';
import Button from '@mui/material/Button'
// import Button from '@material-ui/core/Button';
import * as React from 'react'

type Props = {}

export default function Home({}: Props) {
  return (
    <div>
      <h1>Home</h1>
      <Button variant="contained">My new Button</Button>
    </div>
  )
}