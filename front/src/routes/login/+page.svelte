<div class="min-h-screen py-6 flex flex-col justify-center sm:py-12">
	<div class="w-full relative py-3 sm:max-w-md sm:mx-auto">
		<div class="absolute inset-0 bg-gradient-to-r from-blue-300 to-blue-600 shadow-lg transform -skew-y-6 sm:skew-y-0 sm:-rotate-6 sm:rounded-3xl"></div>
		<div class="w-full relative px-4 py-10 bg-white shadow-lg sm:rounded-3xl">
			<div class="max-w-md mx-auto">
				<div class="space-y-4 md:space-y-6 sm:p-8">
          <a href="/">
            <img class="mx-auto h-10 w-auto" src="/logo.png" alt="logo">
          </a>
					<h1 class="mt-6 text-center text-xl font-bold sm:text-2xl">
            Welcome back to Ground
					</h1>
					<div>
						<span class="text-sm font-medium">SIGN IN WITH</span>
						<div class="mt-1 grid grid-cols-3 gap-3">
							<a class="inline-flex w-full justify-center rounded-md border border-gray-500/30 px-4 py-2 text-gray-700 shadow-sm hover:border-gray-500" href="http://localhost:8080/oauth2/authorization/github">
								<span class="sr-only">Sign in with GitHub</span>
								<svg class="h-5 w-5" aria-hidden="true" fill="currentColor" viewBox="0 0 20 20">
									<path fill-rule="evenodd" d="M10 0C4.477 0 0 4.484 0 10.017c0 4.425 2.865 8.18 6.839 9.504.5.092.682-.217.682-.483 0-.237-.008-.868-.013-1.703-2.782.605-3.369-1.343-3.369-1.343-.454-1.158-1.11-1.466-1.11-1.466-.908-.62.069-.608.069-.608 1.003.07 1.531 1.032 1.531 1.032.892 1.53 2.341 1.088 2.91.832.092-.647.35-1.088.636-1.338-2.22-.253-4.555-1.113-4.555-4.951 0-1.093.39-1.988 1.029-2.688-.103-.253-.446-1.272.098-2.65 0 0 .84-.27 2.75 1.026A9.564 9.564 0 0110 4.844c.85.004 1.705.115 2.504.337 1.909-1.296 2.747-1.027 2.747-1.027.546 1.379.203 2.398.1 2.651.64.7 1.028 1.595 1.028 2.688 0 3.848-2.339 4.695-4.566 4.942.359.31.678.921.678 1.856 0 1.338-.012 2.419-.012 2.747 0 .268.18.58.688.482A10.019 10.019 0 0020 10.017C20 4.484 15.522 0 10 0z" clip-rule="evenodd"></path>
								</svg>
							</a>
							<a class="inline-flex w-full justify-center rounded-md border border-gray-500/30 px-4 py-2 text-gray-700 shadow-sm hover:border-gray-500/70" href="http://localhost:8080/oauth2/authorization/google">
								<span class="sr-only">Sign in with Google</span>
								<svg class="h-5 w-5" viewBox="0 0 18 18" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
									<g clip-path="url(#clip0_1394_6209)">
										<path fill-rule="evenodd" clip-rule="evenodd" d="M17.4982 9.20447C17.4982 8.56603 17.4427 7.95303 17.3405 7.36377H9.17188V10.8451H13.84C13.639 11.9702 13.0278 12.9232 12.1094 13.5616V15.8194H14.9129C16.5528 14.2526 17.4991 11.9456 17.4991 9.20447H17.4982Z" fill="currentColor"></path>
										<path fill-rule="evenodd" clip-rule="evenodd" d="M9.17299 17.9998C11.5148 17.9998 13.4784 17.1935 14.9132 15.8191L12.1097 13.5613C11.3326 14.1014 10.3398 14.4202 9.17218 14.4202C6.91286 14.4202 5.00082 12.8372 4.31853 10.71H1.42188V13.0416C2.84937 15.9828 5.78198 17.9998 9.17299 17.9998Z" fill="currentColor"></path>
										<path fill-rule="evenodd" clip-rule="evenodd" d="M4.32 10.7101C4.14677 10.17 4.0479 9.59348 4.0479 8.99999C4.0479 8.40649 4.14677 7.82994 4.32 7.28986V4.95825H1.42252C0.835016 6.17323 0.5 7.54761 0.5 8.99999C0.5 10.4524 0.835016 11.8267 1.42252 13.0417L4.32 10.7101Z" fill="currentColor"></path>
										<path fill-rule="evenodd" clip-rule="evenodd" d="M9.17299 3.57965C10.4461 3.57965 11.59 4.0341 12.4888 4.9252L14.9769 2.34348C13.4743 0.891945 11.5107 0 9.17299 0C5.78198 0 2.84937 2.01705 1.42188 4.95827L4.31935 7.28987C5.00164 5.1626 6.91368 3.57965 9.17299 3.57965Z" fill="currentColor"></path>
									</g>
									<defs>
										<clipPath id="clip0_1394_6209">
											<rect width="17" height="18" fill="currentColor" transform="translate(0.5)"></rect>
										</clipPath>
									</defs>
								</svg>
							</a>
							<a class="inline-flex w-full justify-center rounded-md border border-gray-500/30 px-4 py-2 text-gray-700 shadow-sm hover:border-gray-500 " href="http://localhost:8080/oauth2/authorization/facebook">
								<span class="sr-only">Sign in with Facebook</span>
								<svg class="h-5 w-5" aria-hidden="true" fill="currentColor" viewBox="0 0 20 20">
									<path fill-rule="evenodd" d="M20 10c0-5.523-4.477-10-10-10S0 4.477 0 10c0 4.991 3.657 9.128 8.438 9.878v-6.987h-2.54V10h2.54V7.797c0-2.506 1.492-3.89 3.777-3.89 1.094 0 2.238.195 2.238.195v2.46h-1.26c-1.243 0-1.63.771-1.63 1.562V10h2.773l-.443 2.89h-2.33v6.988C16.343 19.128 20 14.991 20 10z" clip-rule="evenodd"></path>
								</svg>
							</a>
							<a class="inline-flex w-full justify-center rounded-md border border-gray-500/30 px-4 py-2 text-gray-700 shadow-sm hover:border-gray-500" href="http://localhost:8080/oauth2/authorization/naver">
								<span class="sr-only">Sign in with Naver</span>
								<svg class="h-5 w-5" viewBox="-1 -1 17 16" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
									<path d="M9.88343 0V7.06382L5.13527 0H0V14H5.11657V6.93618L9.8666 14H15V0H9.88343Z" fill="currentColor"></path>
								</svg>
							</a>
							<a class="inline-flex w-full justify-center rounded-md border border-gray-500/30 px-4 py-2 text-gray-700 shadow-sm hover:border-gray-500" href="http://localhost:8080/oauth2/authorization/kakao">
								<span class="sr-only">Sign in with Kakao</span>
								<svg class="h-5 w-5" viewBox="0 0 22 21" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
									<g clip-path="url(#clip0_1394_6217)">
										<path d="M11 0C4.92473 0 0 4.04523 0 9.03544C0 12.2837 2.08683 15.1299 5.2182 16.7227C4.98798 17.6156 4.38507 19.9561 4.26426 20.457C4.11553 21.0787 4.48366 21.0705 4.72471 20.9029C4.9139 20.772 7.73926 18.7767 8.95819 17.9152C9.6198 18.017 10.3019 18.0709 11 18.0709C17.0753 18.0709 22 14.0251 22 9.03544C22 4.04582 17.0753 0 11 0Z" fill="currentColor"></path>
										<path d="M6.57195 6.37451H3.35566C3.05306 6.37451 2.80859 6.62911 2.80859 6.94293C2.80859 7.25674 3.05363 7.51134 3.35566 7.51134H4.40135V11.5151C4.40135 11.6608 4.45834 11.8041 4.55863 11.9077C4.65665 12.0107 4.79398 12.0699 4.93474 12.0699H5.05156C5.19175 12.0699 5.32851 12.0107 5.4271 11.9077C5.52739 11.8041 5.58438 11.6614 5.58438 11.5151V7.51134H6.57195C6.87454 7.51134 7.11958 7.25674 7.11958 6.94293C7.11958 6.62911 6.87454 6.37451 6.57195 6.37451Z" fill="white" class="block dark:hidden"></path>
										<path d="M14.3034 11.0004H12.8075V6.9114C12.8075 6.57035 12.542 6.29443 12.2143 6.29443C11.8866 6.29443 11.6211 6.57035 11.6211 6.9114V11.319C11.6211 11.3533 11.6251 11.3853 11.6302 11.4179C11.6245 11.4498 11.6211 11.483 11.6211 11.5162C11.6211 11.8016 11.8428 12.0325 12.1174 12.0325H14.3034C14.5787 12.0325 14.7998 11.8016 14.7998 11.5162C14.7998 11.2308 14.5781 11.0004 14.3034 11.0004Z" fill="white" class="block dark:hidden"></path>
										<path d="M19.0842 11.1881L17.4174 8.91329L18.9235 7.34778C19.1275 7.1364 19.1275 6.79239 18.9235 6.58042C18.7195 6.36845 18.389 6.36845 18.1844 6.58042L16.3198 8.51777V6.9114C16.3198 6.57035 16.0543 6.29443 15.7266 6.29443C15.3989 6.29443 15.1328 6.57035 15.1328 6.9114V11.4546C15.1328 11.7956 15.3984 12.0716 15.7266 12.0716C16.0548 12.0716 16.3198 11.7962 16.3198 11.4546V10.0531L16.6589 9.70138L18.2357 11.8513C18.4112 12.0946 18.7446 12.145 18.9788 11.9608C19.213 11.7779 19.2603 11.4321 19.0842 11.1881Z" fill="white" class="block dark:hidden"></path>
										<path d="M11.1745 11.3357L9.57146 6.77887C9.57146 6.77887 9.56747 6.77532 9.56633 6.77355C9.46375 6.49585 9.168 6.29395 8.81525 6.29395C8.42376 6.29395 8.10008 6.54263 8.03568 6.86769C8.02486 6.89197 8.01403 6.91624 8.00491 6.94229L6.42868 11.3357C6.33124 11.624 6.47712 11.939 6.7535 12.0403C7.03045 12.1415 7.33419 11.9899 7.43163 11.7022L7.7046 10.8934H9.89856L10.1709 11.7022C10.2684 11.9899 10.5721 12.1415 10.8491 12.0403C11.126 11.939 11.2719 11.624 11.1745 11.3357ZM8.05278 9.86016L8.79018 7.67413C8.79873 7.67413 8.80557 7.6765 8.81411 7.6765L9.55094 9.86016H8.05335H8.05278Z" fill="white" class="block dark:hidden"></path>
										<path d="M6.57195 6.37451H3.35566C3.05306 6.37451 2.80859 6.62911 2.80859 6.94293C2.80859 7.25674 3.05363 7.51134 3.35566 7.51134H4.40135V11.5151C4.40135 11.6608 4.45834 11.8041 4.55863 11.9077C4.65665 12.0107 4.79398 12.0699 4.93474 12.0699H5.05156C5.19175 12.0699 5.32851 12.0107 5.4271 11.9077C5.52739 11.8041 5.58438 11.6614 5.58438 11.5151V7.51134H6.57195C6.87454 7.51134 7.11958 7.25674 7.11958 6.94293C7.11958 6.62911 6.87454 6.37451 6.57195 6.37451Z" fill="white" class="hidden dark:block"></path>
										<path d="M14.3034 11.0004H12.8075V6.9114C12.8075 6.57035 12.542 6.29443 12.2143 6.29443C11.8866 6.29443 11.6211 6.57035 11.6211 6.9114V11.319C11.6211 11.3533 11.6251 11.3853 11.6302 11.4179C11.6245 11.4498 11.6211 11.483 11.6211 11.5162C11.6211 11.8016 11.8428 12.0325 12.1174 12.0325H14.3034C14.5787 12.0325 14.7998 11.8016 14.7998 11.5162C14.7998 11.2308 14.5781 11.0004 14.3034 11.0004Z" fill="white" class="hidden dark:block"></path>
										<path d="M19.0842 11.1881L17.4174 8.91329L18.9235 7.34778C19.1275 7.1364 19.1275 6.79239 18.9235 6.58042C18.7195 6.36845 18.389 6.36845 18.1844 6.58042L16.3198 8.51777V6.9114C16.3198 6.57035 16.0543 6.29443 15.7266 6.29443C15.3989 6.29443 15.1328 6.57035 15.1328 6.9114V11.4546C15.1328 11.7956 15.3984 12.0716 15.7266 12.0716C16.0548 12.0716 16.3198 11.7962 16.3198 11.4546V10.0531L16.6589 9.70138L18.2357 11.8513C18.4112 12.0946 18.7446 12.145 18.9788 11.9608C19.213 11.7779 19.2603 11.4321 19.0842 11.1881Z" fill="white" class="hidden dark:block"></path>
										<path d="M11.1745 11.3357L9.57146 6.77887C9.57146 6.77887 9.56747 6.77532 9.56633 6.77355C9.46375 6.49585 9.168 6.29395 8.81525 6.29395C8.42376 6.29395 8.10008 6.54263 8.03568 6.86769C8.02486 6.89197 8.01403 6.91624 8.00491 6.94229L6.42868 11.3357C6.33124 11.624 6.47712 11.939 6.7535 12.0403C7.03045 12.1415 7.33419 11.9899 7.43163 11.7022L7.7046 10.8934H9.89856L10.1709 11.7022C10.2684 11.9899 10.5721 12.1415 10.8491 12.0403C11.126 11.939 11.2719 11.624 11.1745 11.3357ZM8.05278 9.86016L8.79018 7.67413C8.79873 7.67413 8.80557 7.6765 8.81411 7.6765L9.55094 9.86016H8.05335H8.05278Z" fill="white" class="hidden dark:block"></path>
									</g>
									<defs>
										<clipPath id="clip0_1394_6217">
											<rect width="22" height="21"></rect>
										</clipPath>
									</defs>
								</svg>
							</a>
							<a class="inline-flex w-full justify-center rounded-md border border-gray-500/30 px-4 py-2 text-gray-700 shadow-sm hover:border-gray-500" href="http://localhost:8080/oauth2/authorization/discord">
								<span class="sr-only">Sign in with Discord</span>
								<svg class="h-5 w-5"  xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 -28.5 256 256" version="1.1" preserveAspectRatio="xMidYMid">
                  <g>
                      <path d="M216.856339,16.5966031 C200.285002,8.84328665 182.566144,3.2084988 164.041564,0 C161.766523,4.11318106 159.108624,9.64549908 157.276099,14.0464379 C137.583995,11.0849896 118.072967,11.0849896 98.7430163,14.0464379 C96.9108417,9.64549908 94.1925838,4.11318106 91.8971895,0 C73.3526068,3.2084988 55.6133949,8.86399117 39.0420583,16.6376612 C5.61752293,67.146514 -3.4433191,116.400813 1.08711069,164.955721 C23.2560196,181.510915 44.7403634,191.567697 65.8621325,198.148576 C71.0772151,190.971126 75.7283628,183.341335 79.7352139,175.300261 C72.104019,172.400575 64.7949724,168.822202 57.8887866,164.667963 C59.7209612,163.310589 61.5131304,161.891452 63.2445898,160.431257 C105.36741,180.133187 151.134928,180.133187 192.754523,160.431257 C194.506336,161.891452 196.298154,163.310589 198.110326,164.667963 C191.183787,168.842556 183.854737,172.420929 176.223542,175.320965 C180.230393,183.341335 184.861538,190.991831 190.096624,198.16893 C211.238746,191.588051 232.743023,181.531619 254.911949,164.955721 C260.227747,108.668201 245.831087,59.8662432 216.856339,16.5966031 Z M85.4738752,135.09489 C72.8290281,135.09489 62.4592217,123.290155 62.4592217,108.914901 C62.4592217,94.5396472 72.607595,82.7145587 85.4738752,82.7145587 C98.3405064,82.7145587 108.709962,94.5189427 108.488529,108.914901 C108.508531,123.290155 98.3405064,135.09489 85.4738752,135.09489 Z M170.525237,135.09489 C157.88039,135.09489 147.510584,123.290155 147.510584,108.914901 C147.510584,94.5396472 157.658606,82.7145587 170.525237,82.7145587 C183.391518,82.7145587 193.761324,94.5189427 193.539891,108.914901 C193.539891,123.290155 183.391518,135.09489 170.525237,135.09489 Z" fill="#4A5568" fill-rule="nonzero"></path>
                  </g>
                </svg>
							</a>
						</div>
					</div>
					<div class="relative mt-7">
						<div class="absolute inset-0 flex items-center">
							<div class="w-full border-t border-gray-500/30 dark:border-gray-500/70"></div>
						</div>
						<div class="relative flex justify-center text-sm">
							<span class="bg-white px-2 text-gray-500">GROUND ACCOUNT</span>
						</div>
					</div>
					<form class="space-y-4 md:space-y-6" action="#">
						<div>
							<label for="email" class="block mb-2 text-sm font-medium text-gray-700 ">Your email</label>
							<input type="email" name="email" id="email" class="block w-full rounded-md border border-gray-500/30 px-3 py-2 text-sm placeholder-gray-300/80 shadow-sm focus:border-gray-500 focus:outline-none focus:ring-0 sm:text-base"  placeholder="Enter your email" required="">
						</div>
						<div>
							<label for="password" class="block mb-2 text-sm font-medium text-gray-700 ">Password</label>
							<input type="password" name="password" id="password" placeholder="••••••••" class="block w-full rounded-md border border-gray-500/30 px-3 py-2 text-sm placeholder-gray-300/80 shadow-sm focus:border-gray-500 focus:outline-none focus:ring-0 sm:text-base" required="">
						</div>
						<div class="flex items-center justify-between">
							<div class="flex items-start">
								<div class="flex items-center h-5">
									<input id="remember" aria-describedby="remember" type="checkbox" class="w-4 h-4 border border-gray-300 rounded bg-gray-50 focus:ring-3 focus:ring-primary-300 dark:bg-gray-700 dark:border-gray-600 dark:focus:ring-primary-600" required="">
								</div>
								<div class="ml-3 text-sm">
									<label for="remember" class="text-gray-700">Remember me</label>
								</div>
							</div>
							<a href="#" class="text-sm font-medium text-blue-400 hover:underline">Forgot password?</a>
						</div>
						<button type="submit" class="w-full text-white bg-blue-600 hover:bg-primary-700 focus:ring-4 focus:outline-none focus:ring-primary-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center">Sign in</button>
						<p class="text-sm font-light text-gray-800">
							Don’t have an account yet? <a href="/signup" class="font-medium text-blue-400 hover:underline">Sign up</a>
						</p>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>
