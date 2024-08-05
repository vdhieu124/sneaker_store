
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- directive của JSTL -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Navbar -->
<nav class="navbar navbar-expand-lg navbar-light bg-white flex-column border-0  ">
  <div class="container-fluid">
    <div class="w-100">
      <div class="d-flex justify-content-between align-items-center flex-wrap">

        <!-- Logo-->
        <a class="navbar-brand fw-bold fs-3 m-0 p-0 flex-shrink-0 order-0" href="${classpath}/index">
          <div class="d-flex align-items-center">
            <svg class="f-w-7" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 77.53 72.26"><path d="M10.43,54.2h0L0,36.13,10.43,18.06,20.86,0H41.72L10.43,54.2Zm67.1-7.83L73,54.2,68.49,62,45,48.47,31.29,72.26H20.86l-5.22-9L52.15,0H62.58l5.21,9L54.06,32.82,77.53,46.37Z" fill="currentColor" fill-rule="evenodd"/></svg>
          </div>
        </a>
        <!-- / Logo-->

        <!-- Navbar Icons-->
        <ul class="list-unstyled mb-0 d-flex align-items-center order-1 order-lg-2 nav-sidelinks">

          <!-- Mobile Nav Toggler-->
          <li class="d-lg-none">
                            <span class="nav-link text-body d-flex align-items-center cursor-pointer" data-bs-toggle="collapse"
                                  data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false"
                                  aria-label="Toggle navigation"><i class="ri-menu-line ri-lg me-1"></i> Menu</span>
          </li>
          <!-- /Mobile Nav Toggler-->

          <!-- Navbar Login-->
          <li class="ms-1 d-none d-lg-inline-block">
            <c:choose>
              <c:when test="${isLogined }">
                <a class="nav-link text-body" href="#">${loginedUser.name }</a>
              </c:when>
              <c:otherwise>
                <a class="nav-link text-body" href="${classpath}/login">Login</a>
              </c:otherwise>
            </c:choose>
          </li>

          <!-- /Navbar Login-->

          <!-- Navbar Search-->
          <li class="d-none d-sm-block">
            <span class="nav-link text-body search-trigger cursor-pointer">Search</span>

            <!-- Search navbar overlay-->
            <div class="navbar-search d-none">
              <div class="input-group mb-3 h-100">
                                    <span class="input-group-text px-4 bg-transparent border-0"><i
                                            class="ri-search-line ri-lg"></i></span>
                <input type="text" class="form-control text-body bg-transparent border-0"
                       placeholder="Enter your search terms...">
                <span
                        class="input-group-text px-4 cursor-pointer disable-child-pointer close-search bg-transparent border-0"><i
                        class="ri-close-circle-line ri-lg"></i></span>
              </div>
            </div>
            <div class="search-overlay"></div>
            <!-- / Search navbar overlay-->

          </li>
          <!-- /Navbar Search-->

          <!-- Navbar Cart Icon-->
          <li class="ms-1 d-inline-block position-relative dropdown-cart">
            <button class="nav-link me-0 disable-child-pointer border-0 p-0 bg-transparent text-body"
                    type="button">
              Cart (<span class="totalCartProductsId">${totalCartProducts}</span>)
            </button>
            <div class="cart-dropdown dropdown-menu">

              <!-- Cart Header-->
              <div class="d-flex justify-content-between align-items-center border-bottom pt-3 pb-4">
                <h6 class="fw-bolder m-0">Cart Summary (<span class="totalCartProductsId">${totalCartProducts}</span> items)</h6>
                <i class="ri-close-circle-line text-muted ri-lg cursor-pointer btn-close-cart"></i>
              </div>
              <!-- / Cart Header-->

              <!-- Cart Items-->
              <div>
                <c:forEach items="${cart.cartProducts}" var="item">
                  <!-- Cart Product-->
                  <div class="row mx-0 py-4 g-0 border-bottom">
                    <div class="col-2 position-relative">
                      <picture class="d-block ">
                        <img class="img-fluid" src="${classpath}/FileUploads/${item.avatar }" alt="HTML Bootstrap Template by Pixel Rocket">
                      </picture>
                    </div>
                    <div class="col-9 offset-1">
                      <div>
                        <h6 class="justify-content-between d-flex align-items-start mb-2">
                          ${item.productName}
                          <i class="ri-close-line ms-3"></i>
                        </h6>
                        <span class="d-block text-muted fw-bolder text-uppercase fs-9">Qty: ${item.quantity}</span>
                      </div>
                      <p class="value fw-bolder text-end text-muted m-0">${item.price * item.quantity }VND</p>
                    </div>
                  </div>
                </c:forEach>

              </div>
              <!-- /Cart Items-->

              <!-- Cart Summary-->
              <div>
                <div class="pt-3">
                  <div class="d-flex flex-column flex-md-row justify-content-md-between align-items-md-start mb-4 mb-md-2">
                    <div>
                      <p class="m-0 fw-bold fs-5">Grand Total</p>
                    </div>
                    <p class="sum m-0 fs-5 fw-bold"></p>
                  </div>
                </div>
                <a href="${classpath}/cart-view" class="btn btn-outline-dark w-100 text-center mt-4" role="button">View Cart</a>
              </div>
              <!-- / Cart Summary-->
            </div>


          </li>
          <!-- /Navbar Cart Icon-->

          <!-- LOGOUT -->
          <c:choose>
            <c:when test="${isLogined }">
              <li class="ms-1 d-none d-lg-inline-block">
                <a class="nav-link text-body" href="${classpath}/logout">Logout</a>
              </li>
            </c:when>
          </c:choose>

        </ul>
        <!-- Navbar Icons-->

        <!-- Main Navigation-->
        <div class="flex-shrink-0 collapse navbar-collapse navbar-collapse-light w-auto flex-grow-1 order-2 order-lg-1"
             id="navbarNavDropdown">

          <!-- Menu-->
          <ul class="navbar-nav mx-auto mb-2 mb-lg-0">
            <li class="nav-item dropdown dropdown position-static">
              <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                All
              </a>
              <!-- Menswear dropdown menu-->
              <div class="dropdown-menu dropdown-megamenu">
                <div class="container-fluid">
                  <div class="row g-0 g-lg-3">
                    <!-- Menswear Dropdown Menu Links Section-->
                    <div class="col col-lg-8 py-lg-5">
                      <div class="row py-3 py-lg-0 flex-wrap gx-4 gy-6">
                        <!-- menu row-->
                        <div class="col">
                          <h6 class="dropdown-heading">Coats & Jackets</h6>
                          <ul class="list-unstyled">
                            <li class="dropdown-list-item"><a class="dropdown-item" href="${classpath }/category">Waterproof Jackets</a></li>
                            <li class="dropdown-list-item"><a class="dropdown-item" href="${classpath }/category">Insulated Jackets</a></li>
                            <li class="dropdown-list-item"><a class="dropdown-item" href="${classpath }/category">Down Jackets</a></li>
                            <li class="dropdown-list-item"><a class="dropdown-item" href="${classpath }/category">Softshell Jackets</a></li>
                            <li class="dropdown-list-item"><a class="dropdown-item" href="${classpath }/category">Casual Jackets</a></li>
                            <li class="dropdown-list-item"><a class="dropdown-item" href="${classpath }/category">Windproof Jackets</a></li>
                            <li class="dropdown-list-item"><a class="dropdown-item" href="${classpath }/category">Breathable Jackets</a></li>
                            <li class="dropdown-list-item"><a class="dropdown-item" href="${classpath }/category">Cleaning & Proofing</a></li>
                            <li class="dropdown-list-item"><a class="dropdown-item dropdown-link-all" href="${classpath }/category">View All</a></li>
                          </ul>
                        </div>
                        <!-- / menu row-->

                        <!-- menu row-->
                        <div class="col">
                          <h6 class="dropdown-heading">Insulated</h6>
                          <ul class="list-unstyled">
                            <li class="dropdown-list-item"><a class="dropdown-item" href="${classpath }/category">Insulated Jackets</a></li>
                            <li class="dropdown-list-item"><a class="dropdown-item" href="${classpath }/category">Bodywarmers</a></li>
                            <li class="dropdown-list-item"><a class="dropdown-item" href="${classpath }/category">Parkas</a></li>
                            <li class="dropdown-list-item"><a class="dropdown-item" href="${classpath }/category">Baselayers & Thermals</a></li>
                            <li class="dropdown-list-item"><a class="dropdown-item" href="${classpath }/category">Winter Hats</a></li>
                            <li class="dropdown-list-item"><a class="dropdown-item" href="${classpath }/category">Scarves & Neck</a></li>
                            <li class="dropdown-list-item"><a class="dropdown-item" href="${classpath }/category">Gloves & Mitts</a></li>
                            <li class="dropdown-list-item"><a class="dropdown-item" href="${classpath }/category">Accessories</a></li>
                            <li class="dropdown-list-item"><a class="dropdown-item dropdown-link-all" href="${classpath }/category">View All</a></li>
                          </ul>
                        </div>
                        <!-- / menu row-->

                        <!-- menu row-->
                        <div class="d-none d-xxl-block col">
                          <h6 class="dropdown-heading">Footwear</h6>
                          <ul class="list-unstyled">
                            <li class="dropdown-list-item"><a class="dropdown-item" href="${classpath }/category">Lifestyle & Casual</a></li>
                            <li class="dropdown-list-item"><a class="dropdown-item" href="${classpath }/category">Walking Shoes</a></li>
                            <li class="dropdown-list-item"><a class="dropdown-item" href="${classpath }/category">Running Shoes</a></li>
                            <li class="dropdown-list-item"><a class="dropdown-item" href="${classpath }/category">Military Boots</a></li>
                            <li class="dropdown-list-item"><a class="dropdown-item" href="${classpath }/category">Fabric Walking Boots</a></li>
                            <li class="dropdown-list-item"><a class="dropdown-item" href="${classpath }/category">Leather Walking Boots</a></li>
                            <li class="dropdown-list-item"><a class="dropdown-item" href="${classpath }/category">Wellies</a></li>
                            <li class="dropdown-list-item"><a class="dropdown-item" href="${classpath }/category">Winter Footwear</a></li>
                            <li class="dropdown-list-item"><a class="dropdown-item dropdown-link-all" href="${classpath }/category">View All</a></li>
                          </ul>
                        </div>
                        <!-- / menu row-->

                        <!-- menu row-->
                        <div class="col">
                          <h6 class="dropdown-heading text-danger">Special Offers</h6>
                          <ul class="list-unstyled">
                            <li class="dropdown-list-item"><a class="dropdown-item text-danger" href="${classpath }/category">Insulated Jackets</a></li>
                            <li class="dropdown-list-item"><a class="dropdown-item text-danger" href="${classpath }/category">Bodywarmers</a></li>
                            <li class="dropdown-list-item"><a class="dropdown-item text-danger" href="${classpath }/category">Parkas</a></li>
                            <li class="dropdown-list-item"><a class="dropdown-item text-danger" href="${classpath }/category">Baselayers & Thermals</a></li>
                            <li class="dropdown-list-item"><a class="dropdown-item text-danger" href="${classpath }/category">Winter Hats</a></li>
                            <li class="dropdown-list-item"><a class="dropdown-item text-danger" href="${classpath }/category">Scarves & Neck</a></li>
                            <li class="dropdown-list-item"><a class="dropdown-item text-danger" href="${classpath }/category">Gloves & Mitts</a></li>
                            <li class="dropdown-list-item"><a class="dropdown-item text-danger" href="${classpath }/category">Accessories</a></li>
                            <li class="dropdown-list-item"><a class="dropdown-item text-danger dropdown-link-all" href="${classpath }/category">View All</a></li>
                          </ul>
                        </div>
                        <!-- / menu row-->
                      </div>

                      <div class="align-items-center justify-content-between mt-5 d-none d-lg-flex">
                        <div class="me-5 f-w-20">
                          <a class="d-block" href="${classpath }/category">
                            <picture>
                              <img class="img-fluid d-table mx-auto" src="./assets/images/logos/logo-1.svg" alt="">
                            </picture>
                          </a>
                        </div>
                        <div class="me-5 f-w-20">
                          <a class="d-block" href="${classpath }/category">
                            <picture>
                              <img class="img-fluid d-table mx-auto" src="./assets/images/logos/logo-2.svg" alt="">
                            </picture>
                          </a>
                        </div>
                        <div class="me-5 f-w-20">
                          <a class="d-block" href="${classpath }/category">
                            <picture>
                              <img class="img-fluid d-table mx-auto" src="./assets/images/logos/logo-3.svg" alt="">
                            </picture>
                          </a>
                        </div>
                        <div class="me-5 f-w-20">
                          <a class="d-block" href="${classpath }/category">
                            <picture>
                              <img class="img-fluid d-table mx-auto" src="./assets/images/logos/logo-4.svg" alt="">
                            </picture>
                          </a>
                        </div>
                        <div class="me-5 f-w-20">
                          <a class="d-block" href="${classpath }/category">
                            <picture>
                              <img class="img-fluid d-table mx-auto" src="./assets/images/logos/logo-5.svg" alt="">
                            </picture>
                          </a>
                        </div>
                        <div class="me-5 f-w-20">
                          <a class="d-block" href="${classpath }/category">
                            <picture>
                              <img class="img-fluid d-table mx-auto" src="./assets/images/logos/logo-6.svg" alt="">
                            </picture>
                          </a>
                        </div>
                      </div>                  </div>
                    <!-- /Menswear Dropdown Menu Links Section-->

                    <!-- Menswear Dropdown Menu Images Section-->
                    <div class="col-lg-4 d-none d-lg-block">
                      <div class="vw-50 border-start h-100 position-absolute"></div>
                      <div class="py-lg-5 position-relative z-index-10 px-lg-4">
                        <div class="row g-4">
                          <div class="col-12 col-md-6">
                            <div class="card justify-content-center d-flex align-items-center bg-transparent">
                              <picture class="w-100 d-block mb-2 mx-auto">
                                <img class="w-100 rounded" title="" src="${classpath}/frontend/images/banners/banner-12.jpg" alt="HTML Bootstrap Template by Pixel Rocket">
                              </picture>
                              <a class="fw-bolder link-cover" href="${classpath }/category">Latest Arrivals</a>
                            </div>
                          </div>
                          <div class="col-12 col-md-6">
                            <div class="card justify-content-center d-flex align-items-center bg-transparent">
                              <picture class="w-100 d-block mb-2 mx-auto">
                                <img class="w-100 rounded" title="" src="${classpath}/frontend/images/banners/banner-13.jpg" alt="HTML Bootstrap Template by Pixel Rocket">
                              </picture>
                              <a class="fw-bolder link-cover" href="${classpath }/category">Accessories</a>
                            </div>
                          </div>
                          <div class="col-12 col-md-6">
                            <div class="card justify-content-center d-flex align-items-center bg-transparent">
                              <picture class="w-100 d-block mb-2 mx-auto">
                                <img class="w-100 rounded" title="" src="${classpath}/frontend/images/banners/banner-14.jpg" alt="HTML Bootstrap Template by Pixel Rocket">
                              </picture>
                              <a class="fw-bolder link-cover" href="${classpath }/category">T-Shirts</a>
                            </div>
                          </div>
                          <div class="col-12 col-md-6">
                            <div class="card justify-content-center d-flex align-items-center bg-transparent">
                              <picture class="w-100 d-block mb-2 mx-auto">
                                <img class="w-100 rounded" title="" src="${classpath}/frontend/images/banners/banner-15.jpg" alt="HTML Bootstrap Template by Pixel Rocket">
                              </picture>
                              <a class="fw-bolder link-cover" href="${classpath }/category">Jackets</a>
                            </div>
                          </div>
                        </div>
                        <a href="${classpath }/category" class="btn btn-link p-0 fw-bolder text-link-border mt-5 text-dark mx-auto d-table">Visit Mens Section</a>
                      </div>
                    </div>
                    <!-- Menswear Dropdown Menu Images Section-->
                  </div>
                </div>
              </div>
              <!-- / Menswear dropdown menu-->
            </li>
            <li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle" href="${classpath }/category" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                Category
              </a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="${classpath }/contact" role="button">
                Contact
              </a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#" role="button">
                About
              </a>
            </li>
            <li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                Pages
              </a>
              <ul class="dropdown-menu">
                <li><a class="dropdown-item" href="${classpath }/index">Homepage</a></li>
                <li><a class="dropdown-item" href="${classpath }/category">Category</a></li>
                <li><a class="dropdown-item" href="${classpath }/contact">Contact</a></li>
              </ul>
            </li>
          </ul>                    <!-- / Menu-->

        </div>
        <!-- / Main Navigation-->

      </div>
    </div>
  </div>
</nav>
<!-- / Navbar-->

<script>
  window.onload = function() {
    var paragraphs = document.querySelectorAll('.value');
    var sum = 0;

    for (var i = 0; i < paragraphs.length; i++) {
      var value = paragraphs[i].innerHTML.slice(0, -3);
      sum += parseInt(value);
    }
    var formattedSum = sum.toLocaleString(undefined, { minimumFractionDigits: 0, maximumFractionDigits: 0 }); // Format the sum variable with the pattern #,###
    var targetParagraph = document.querySelector('.sum');
    targetParagraph.innerHTML = formattedSum + " VND";
  };
</script>