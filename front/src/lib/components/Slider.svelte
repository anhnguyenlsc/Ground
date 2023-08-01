<script>
    //Khai báo các objects cards và truyền các tham số như title, image, description, tags vào card image
    let cards = [
        { 
            title: "The Coldest Sunset", 
            image: "https://images.unsplash.com/photo-1495616811223-4d98c6e9c869?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1032&q=80", 
            description: "Lorem ipsum dolor sit amet", 
            tags: ["photography"] 
        },
        { 
            title: "The Beautiful Sunshine", 
            image: "https://images.unsplash.com/photo-1500534623283-312aade485b7?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=870&q=80", 
            description: "Lorem ipsum dolor sit amet", 
            tags: ["sun"] 
        },
        { 
            title: "The Coldest Sunset", 
            image: "https://images.unsplash.com/photo-1506744038136-46273834b3fb?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=870&q=80", 
            description: "Lorem ipsum dolor sit amet", 
            tags: ["photography"] 
        },
        { 
            title: "The Coldest Sunset", 
            image: "https://images.unsplash.com/photo-1511884642898-4c92249e20b6?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=870&q=80", 
            description: "Lorem ipsum dolor sit amet", 
            tags: ["photography"] 
        },
        { 
            title: "The Coldest Sunset", 
            image: "https://images.unsplash.com/photo-1434725039720-aaad6dd32dfe?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1042&q=80", 
            description: "Lorem ipsum dolor sit amet", 
            tags: ["photography"] 
        },
        { 
            title: "The Coldest Sunset", 
            image: "https://images.unsplash.com/photo-1682685797365-41f45b562c0a?ixlib=rb-4.0.3&ixid=M3wxMjA3fDF8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=870&q=80", 
            description: "Lorem ipsum dolor sit amet", 
            tags: ["photography"] 
        },
        { 
            title: "The Coldest Sunset", 
            image: "https://images.unsplash.com/photo-1490604001847-b712b0c2f967?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1253&q=80", 
            description: "Lorem ipsum dolor sit amet", 
            tags: ["photography"] 
        },
    ];

  	/**
	 * @type {{ offsetLeft: number; scrollLeft: number; }}
	 */
	let in_slide;
    /**
	 * @type {{ offsetLeft: number; scrollLeft: number; }}
	 */
    let ev_slide;
	/**
		 * @type {number}
		 */
	let startX;
	/**
		 * @type {number}
		 */
	let scrollLeft;
	let isDown = false;
	/**
	 * @param {{ pageX: number; }} e
	*/

	//Bắt hành động khi người dùng click chuột xuống (click giữ)
	function mouseIsDown(e) {
		isDown = true;
		startX = e.pageX - in_slide.offsetLeft;
		scrollLeft = in_slide.scrollLeft;

        startX = e.pageX - ev_slide.offsetLeft;
        scrollLeft = ev_slide.scrollLeft

		// @ts-ignore
		in_slide.style.cursor = 'grabbing';

		// @ts-ignore
        ev_slide.style.cursor = 'grabbing';
	}

	/**
	 * @param {any} e
	*/
	// @ts-ignore
	//Hành động thả chuột
	function mouseUp(e) {
		isDown = false;
		// @ts-ignore
		in_slide.style.cursor = 'default';
        // @ts-ignore
        ev_slide.style.cursor = 'default';
	}

	/**
	 * @param {any} e
	*/
	// @ts-ignore

	//Thả chuột
	function mouseLeave(e) {
		isDown = false;
		// @ts-ignore
		in_slide.style.cursor = 'default';
		// @ts-ignore
        ev_slide.style.cursor = 'default';
	}

	/**
	 * @param {{ preventDefault: () => void; pageX: number; }} e
	*/

	//Bắt hành động kéo chuột (scroll)
	function mouseMove_ins(e) {
		if (isDown) {
		e.preventDefault();
		//Move horizontally
		const x1 = e.pageX - in_slide.offsetLeft;
		const walkX1 = (x1 - startX) * 5;
		in_slide.scrollLeft = scrollLeft - walkX1;        
		}
	}

    /**
	 * @param {{ preventDefault: () => void; pageX: number; }} e
	 */
    function mouseMove_ev(e) {
		if (isDown) {
		e.preventDefault();
		//Move horizontally
        const x2 = e.pageX - ev_slide.offsetLeft;
		const walkX2 = (x2 - startX) * 5;
		ev_slide.scrollLeft = scrollLeft - walkX2;
        
		}
	}
</script>

<!-- INSTANT -->
<section>
    <div class="mt-10">
        <a class="w-full font-semibold hover:text-blue-500 hover:no-underline group-hover:text-blue-500 dark:hover:text-blue-200 dark:text-gray-100 dark:hover:no-underline dark:group-hover:text-blue-200 sm:text-lg" href="">INSTANT</a>
    </div>
    <!-- svelte-ignore a11y-no-static-element-interactions -->
    <div class="slider snap-mandatory snap-x flex overflow-x-scroll scroll-smooth" bind:this={in_slide} on:mousedown|preventDefault={mouseIsDown} on:mouseup|preventDefault={mouseUp} on:mouseleave|preventDefault={mouseLeave} on:mousemove|preventDefault={mouseMove_ins}>
        <!--Nhận vào các object bao gồm các tham số bên trên, có bao nhiêu object sẽ render ra bấy nhiêu card -->
        {#each cards as card}
            <article class="w-full min-w-[200px] m-5 snap-start relative bg-white rounded-xl shadow-lg h-52 sm:h-70 md:h-60 lg:h-52 xl:h-70 hover:shadow-xl hover:transform hover:scale-105 duration-300">
                <div>
                    <img class="w-full h-[140px] rounded-t-xl" src="{card.image}" alt="{card.title}">
                </div>
                <div class="p-1">
                    <a href="" class="text-sm text-sky-700 font-bold hover:underline">{card.title}</a>
                </div>
                <div class="p-1 border-t">
                    {#each card.tags as tag}
                    <p class="text-gray-400 text-sm">
                        #{tag}
                    </p>
                    {/each}	
                </div>
            </article>
        {/each}
    </div>
</section>



<!-- EVENTS -->
<section>
    <div class="mt-10">
        <a class="w-full font-semibold hover:text-blue-500 hover:no-underline group-hover:text-blue-500 dark:hover:text-blue-200 dark:text-gray-100 dark:hover:no-underline dark:group-hover:text-blue-200 sm:text-lg" href="">EVENTS</a>
    </div>
    <!-- svelte-ignore a11y-no-static-element-interactions -->
    <div class="slider snap-mandatory snap-x flex overflow-x-scroll scroll-smooth" bind:this={ev_slide} on:mousedown|preventDefault={mouseIsDown} on:mouseup|preventDefault={mouseUp} on:mouseleave|preventDefault={mouseLeave} on:mousemove|preventDefault={mouseMove_ev}>
        <!--Nhận vào các object bao gồm các tham số bên trên, có bao nhiêu object sẽ render ra bấy nhiêu card -->
        {#each cards as card}
            <article class="w-full min-w-[200px] m-5 snap-start relative bg-white rounded-xl shadow-lg h-52 sm:h-70 md:h-60 lg:h-52 xl:h-70 hover:shadow-xl hover:transform hover:scale-105 duration-300">
                <div>
                    <img class="w-full h-[140px] rounded-t-xl" src="{card.image}" alt="{card.title}">
                </div>
                <div class="p-1">
                    <a href="" class="text-sm text-sky-700 font-bold hover:underline">{card.title}</a>
                </div>
                <div class="p-1 border-t">
                    {#each card.tags as tag}
                    <p class="text-gray-400 text-sm">
                        #{tag}
                    </p>
                    {/each}	
                </div>
            </article>
        {/each}
    </div>
</section>

<style>
    .slider::-webkit-scrollbar {
        display: none;
    }
</style>