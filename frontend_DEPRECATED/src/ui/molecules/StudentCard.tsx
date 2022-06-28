import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import CardMedia from '@mui/material/CardMedia';
import CardActionArea from '@mui/material/CardActionArea';
import CardActions from '@mui/material/CardActions';
import CardHeader from '@mui/material/CardHeader';
import React from 'react'
import { Button, Typography } from '@mui/material';

type Props = {}

export default function StudentCard({}: Props) {
  return (
    <Card variant='outlined' sx={{ maxWidth: {md: 300} }}>
        <CardContent>
            <Typography color='text.secondary' gutterBottom>
                Student
            </Typography>
            <Typography variant='h5' component='div'>Anton Detken</Typography>
            <Typography sx={{ mb: 1.5}} color='text.secondary'>AP20a</Typography>
            <Typography variant='body2'>
                More content here.
            </Typography>

            <CardActions>
                <Button size='small' variant='outlined'>Edit</Button>
            </CardActions>
        </CardContent>
    </Card>
  )
}