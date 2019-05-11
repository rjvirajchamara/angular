package lk.ijse.springboot.app.service.impl;


import lk.ijse.springboot.app.dto.*;
import lk.ijse.springboot.app.entity.*;
import lk.ijse.springboot.app.repository.ItemRepositry;
import lk.ijse.springboot.app.repository.ORderRepository;
import lk.ijse.springboot.app.repository.OrderDetailRepository;
import lk.ijse.springboot.app.service.PlaceOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class   PlaceOrderServiceImpl implements PlaceOrderService {
    @Autowired
    private ItemRepositry itemRepositry;

    @Autowired
    private ORderRepository oRderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean placeOrder(PlaceOrderDTO placeOrderDTO) {

        List<OrderDetailDTO> orderDetailDTOS = placeOrderDTO.getOrderDetailDTOS();

//        OrderDTO orderDTO = placeOrderDTO.getOrderDTO();

//
//        System.out.println("id "+orderDTO.getoId());
//        System.out.println("name "+orderDTO.getCustomer().getName());
//        System.out.println("address "+orderDTO.getCustomer().getAddress());

        //Customer customer=new Customer(orderDTO.getCustomer().getId(),orderDTO.getCustomer().getName(),orderDTO.getCustomer().getAddress());
        OrderDTO orderDTO=new OrderDTO();
        Customer customer=new Customer();
        List<OrderDetails>detailList=new ArrayList<>();
        for(OrderDetailDTO detailsDto : orderDetailDTOS){
            orderDTO=detailsDto.getOrders();
            customer=new Customer(orderDTO.getCustomer().getId(),orderDTO.getCustomer().getName(),orderDTO.getCustomer().getAddress());
            OrderDetails_PK orderDetails_pk=new OrderDetails_PK();
            OrderDetails details=new OrderDetails();
            double newQty=detailsDto.getItem().getQtyOnHand()-detailsDto.getQuantity();
            details.setItem(new Items(detailsDto.getItem().getCode(),detailsDto.getItem().getDiscription(),detailsDto.getItem().getUnicPrice(),(int)newQty));
            details.setQuantity((int) detailsDto.getQuantity());
            details.setUnitprice(detailsDto.getUnitprice());

            orderDetails_pk.setoId(orderDTO.getoId());
            orderDetails_pk.setCode(details.getItem().getCode());
            System.out.println(" price "+detailsDto.getOrders().getTotalPric());
            details.setOrderDetail_PK(orderDetails_pk);
            details.setOrders(null);
            detailList.add(details);
        }

        Orders orders=new Orders(orderDTO.getoId(),orderDTO.getDate(),orderDTO.getTotalPric(),detailList,customer);
        oRderRepository.save(orders);
        return true;
    }

    @Override
    public ArrayList<OrderDTO> getAllOders() {
        List<Orders> ordersList = oRderRepository.findAll();

        ArrayList<OrderDTO> allOrders = new ArrayList<>();

        for (Orders orders : ordersList){
            OrderDTO orderDTO=new OrderDTO();
            orderDTO.setoId(orders.getoId());
            orderDTO.setDate(orders.getDate());
            orderDTO.setTotalPric(orders.getTotalPrice());
            CustomerDTO customer=new CustomerDTO(orders.getCustomer().getId(),orders.getCustomer().getName(),orders.getCustomer().getAddress());
            orderDTO.setCustomer(customer);
            allOrders.add(orderDTO);
        }

        return allOrders;

    }

    @Override
    public long getTotalOrders() {
        long totalOrders = oRderRepository.getTotalOrders();
        return totalOrders;
    }
}
