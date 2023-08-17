<script>
    import axios from "axios";
    let values = Array(6).fill('');
    let token = '';

    // Handle cases of OTP input
    async function handleInput(event, index) {
        const value = event.target.value;
        if (value.length > 1) 
        {
          event.target.value = value.slice(0, 1);
        }
        if (value.length === 1 && index < values.length - 1) 
        {
          const nextInput = document.querySelector(`#input-${index + 1}`);
          if (nextInput) 
          {
            nextInput.focus();
          }
        }

        token += value;
        // console.log('input: ' + token);

        if (token.length === values.length) {
          const response = await axios({
            headers: {
              "Content-Type": "text/plain"
            },
            method: "POST",
            url: "http://localhost:8080/api/authentication/verification",
            data: token
        })

        if (response.data !== null) {
          if (response.data.status === "failed") {
            console.log('Verification failed');
          }
          else {
            location.href = 'http://localhost:5173/#/';
          }
        }
      }
    }

  //   function onKeyDown(event, index) {
  //       if (event.keyCode === 32) { // spacebar + text //|| event.keyCode < 48 || event.keyCode > 57
  //         event.preventDefault();
  //       }
  //       else if (event.keyCode === 8 && index > 0) { // backspace
  //         const preInput = document.querySelector(`#input-${index - 1}`);
  //         const curInput = document.querySelector(`#input-${index}`);

  //         console.log('value: ' + values[index]);
  //         console.log('value - 1: ' + values[index - 1]);

  //         values.pop();
  //         values.push('');
  //         token = token.slice(0, -1); 
  //         // token = values.toString();
  //         console.log('--------> ' + token);

  //         if (curInput) {
  //           preInput.focus();
  //         }
  //       }
  // }
    
</script>

<svelte:head>
	<title>Email verification</title>
	<meta name="description" content="Email verification" />
</svelte:head>

<div class="relative flex min-h-screen flex-col justify-center overflow-hidden bg-gray-45 py-12">
    <div class="relative bg-white px-6 pt-10 pb-9 shadow-xl mx-auto w-full max-w-lg rounded-2xl">
      <div class="mx-auto flex w-full max-w-md flex-col space-y-16">
        <div class="flex flex-col items-center justify-center text-center space-y-2">
          <div class="font-semibold text-3xl">
            <p>Email Verification</p>
          </div>
          <div class="flex flex-row text-sm font-medium text-gray-400">
            <p>We have sent verification code to your email</p>
          </div>
        </div>
  
        <div>
          <form action="" method="post">
            <div class="flex flex-col space-y-16">
                <div class="flex flex-row items-center justify-between mx-auto w-full max-w-sm">
                    {#each values as value, index}
                      <div class="w-52 h-16 mr-2">
                        <input 
                          id="input-{index}"
                          class="w-full h-full flex flex-col items-center justify-center text-center px-5 outline-none rounded-xl border border-gray-200 text-lg bg-gray-45 focus:bg-gray-50 focus:ring-1 ring-blue-700"
                          type="text"
                          maxlength="1"
                          bind:value={values[index]}
                          on:input|preventDefault={(event) => handleInput(event, index)}
                          on:keydown={(event) => onKeyDown(event, index)}
                        />
                      </div>
                    {/each}
                </div>
  
              <div class="flex flex-col space-y-5">
                <div>
                  <button type="submit" class="flex flex-row items-center justify-center text-center w-full border rounded-xl outline-none py-5 bg-blue-700 border-none text-white text-sm shadow-sm">
                    Verify Account
                  </button>
                </div>
  
                <div class="flex flex-row items-center justify-center text-center text-sm font-medium space-x-1 text-gray-500">
                  <p>Didn't recieve code?</p> <a class="flex flex-row items-center text-blue-600" href="http://" target="_blank" rel="noopener noreferrer">Resend</a>
                </div>
              </div>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>