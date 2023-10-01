<script>
	import axios from "axios";
	import { onMount } from "svelte";
  
	// let data = [];
  // let categories = [];

  // onMount(async () => {
	// 	const catApi = 'http://localhost:8080/api/authentication/category';
	// 	const response = await axios({
	// 		method: "GET",
	// 		url: catApi,
	// 	})

	// 	const category = response.data;
	// 	data = category.map((cat) => ({title: cat.title, id: cat.id}));
    
  //   let ids = data.map(item => item.id);
  //   categories = data.map(item => item.title);
	// })

</script>

<nav>
    <div class="wrapper w-full grid grid gap-8 px-14 py-4">
        <ol class="breadcrumbs breadcrumbs--one flex list-none m-0 pl-4 text-sm bg-white overflow-hidden relative outline-none">
          <li class="contents text-white outline-none" itemprop="itemListElement">
            <a class="breadcrumb outline-none" href="#" itemprop="item">
              <span itemprop="name">
                <svg xmlns="http://www.w3.org/2000/svg" class="h-4 w-4" viewBox="0 0 20 20" fill="currentColor">
                    <path d="M10.707 2.293a1 1 0 00-1.414 0l-7 7a1 1 0 001.414 1.414L4 10.414V17a1 1 0 001 1h2a1 1 0 001-1v-2a1 1 0 011-1h2a1 1 0 011 1v2a1 1 0 001 1h2a1 1 0 001-1v-6.586l.293.293a1 1 0 001.414-1.414l-7-7z"></path>
                </svg>
                <span class="px-2">Home</span>
              </span>
            </a>
            <meta itemprop="position" content="1" />
          </li>
          <li class="contents text-white outline-none" itemprop="itemListElement">
            <a class="breadcrumb outline-none" href="#" itemprop="item">
              <span itemprop="name">
                <span>Jane Doe</span>
               </span>
            </a>
            <meta itemprop="position" content="2" />
          </li>
          <li class="contents text-white outline-none" itemprop="itemListElement">
            <a class="breadcrumb outline-none" href="#" itemprop="item">
              <span itemprop="name">
                <span>Jane Doe</span>
               </span>
            </a>
            <meta itemprop="position" content="2" />
          </li>
          <li class="contents text-white outline-none" itemprop="itemListElement">
            <a class="breadcrumb outline-none" href="#" itemprop="item">
              <span itemprop="name">
                <span>Detail</span>
               </span>
            </a>
            <meta itemprop="position" content="2" />
          </li>
          
        </ol>
    </div>
</nav>

<style>
.breadcrumbs li .breadcrumb {
  outline: none;
}

.breadcrumbs li .breadcrumb, .breadcrumbs li [itemprop=name] {
    display: flex;
    align-items: center;
    border-top-left-radius: 2px;
    border-bottom-left-radius: 2px;
}

.breadcrumbs.breadcrumbs--one li .breadcrumb {
    position: relative;
    margin-left: -1rem;
    margin-right: 1rem;
}

.breadcrumbs.breadcrumbs--one li [itemprop=name] {
    padding: 0.5rem 0.5rem 0.5rem 2rem;
}
.breadcrumbs.breadcrumbs--one li a.breadcrumb {
    text-decoration: none;
    /* background: linear-gradient(to right, white 0%, #f2f6f7 50%); */
    background-image: linear-gradient(to left, #3b82f6, #4387f5, #4b8cf5, #5390f4, #5b95f3);
}

.breadcrumbs.breadcrumbs--one li a.breadcrumb:hover, .breadcrumbs.breadcrumbs--one li a.breadcrumb:hover::after, .breadcrumbs.breadcrumbs--one li a.breadcrumb:focus, .breadcrumbs.breadcrumbs--one li a.breadcrumb:focus::after {
    background: #3369dd;
}

.breadcrumbs.breadcrumbs--one li a.breadcrumb::after {
    content: "";
    display: block;
    width: 1.5rem;
    height: 1.5rem;
    z-index: 1;
    margin-right: -0.75rem;
    background-color: rgb(59 130 246);
    border: 0.15rem solid white;
    transform: rotate(45deg) skew(15deg, 15deg);
    transform-origin: center;
    clip-path: polygon(100% 0, 0 0, 100% 100%);
    border-radius: 0 3px 0 0;
}
.breadcrumbs.breadcrumbs--one li:first-child [itemprop=name] {
    padding-left: 1.5rem;
}


</style>