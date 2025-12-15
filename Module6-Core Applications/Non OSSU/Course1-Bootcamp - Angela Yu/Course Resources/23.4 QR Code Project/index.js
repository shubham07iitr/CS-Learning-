/* 
1. Use the inquirer npm package to get user input.
2. Use the qr-image npm package to turn the user entered URL into a QR code image.
3. Create a txt file to save the user input using the native fs node module.
*/

import inquirer from "inquirer";
import qr from "qr-image";
import fs from "fs";

inquirer
  .prompt([
    {
      type: 'input',
      name: 'url',
      message: 'Enter the URL to generate QR code:'
    }
  ])
  .then((answers) => {
    const url = answers.url;
    
    // Generate QR code
    const qr_svg = qr.image(url);
    qr_svg.pipe(fs.createWriteStream('qr_image.png'));
    
    // Save URL to text file
    fs.writeFile('url.txt', url, (err) => {
      if (err) throw err;
      console.log('URL saved!');
    });
  })
  .catch((error) => {
    console.error(error);
  });