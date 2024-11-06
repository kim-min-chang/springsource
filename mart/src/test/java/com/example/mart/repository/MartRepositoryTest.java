package com.example.mart.repository;

import java.time.LocalDateTime;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.mart.entity.constant.DeliveryStatus;
import com.example.mart.entity.constant.OrderStatus;
import com.example.mart.entity.item.Delivery;
import com.example.mart.entity.item.Item;
import com.example.mart.entity.item.Member;
import com.example.mart.entity.item.Order;
import com.example.mart.entity.item.OrderItem;
import com.example.mart.repository.item.DeliveryRepository;
import com.example.mart.repository.item.ItemRepository;
import com.example.mart.repository.item.MemberRepository;
import com.example.mart.repository.item.OrderItemRepository;
import com.example.mart.repository.item.OrderRepository;

import jakarta.transaction.Transactional;

@SpringBootTest
public class MartRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private DeliveryRepository deliveryRepository;

    // c
    @Test
    public void memberInsertTest() {
        memberRepository.save(Member.builder().name("user1").city("서울").street("16-3").zipcode("15350").build());
        memberRepository.save(Member.builder().name("user2").city("부산").street("17-1").zipcode("12345").build());
        memberRepository.save(Member.builder().name("user3").city("인천").street("19-9").zipcode("98765").build());

    }

    @Test
    public void itemInsertTest() {
        itemRepository.save(Item.builder().name("과자").price(30000).quantity(30).build());
        itemRepository.save(Item.builder().name("사과").price(10000).quantity(60).build());
        itemRepository.save(Item.builder().name("미역").price(5000).quantity(100).build());
    }

    @Test
    public void orderInsertTest() {

        Member member = memberRepository.findById(1L).get();
        Item item = itemRepository.findById(1L).get();

        Order order = Order.builder().orderDate(LocalDateTime.now())
                .status(OrderStatus.ORDER)
                .member(member)
                .build();
        orderRepository.save(order);

        OrderItem orderItem = OrderItem.builder()
                .price(10000)
                .count(3)
                .order(order)
                .item(item)
                .build();
        orderItemRepository.save(orderItem);

        // item 수량 감소
    }

    // R
    @Test
    public void memberAndItemandOrderListTest() {
        // 주문 내역 조회
        // orderRepository.findAll().forEach(order -> System.out.println(order));
        // 주문 상세 내역 조회
        orderItemRepository.findAll().forEach(orderItem -> {
            System.out.println(orderItem);
            // 상품 상세 조회
            System.out.println(orderItem.getItem());
            // 주문상세
            System.out.println(orderItem.getOrder());
            // 주문자 상세 조회
            System.out.println(orderItem.getOrder().getMember());
        });
    }

    @Test
    public void memberAndItemAndOrderRowTest() {
        // 주문 내역 조회
        // orderRepository.findAll().forEach(order -> System.out.println(order));
        // 주문 상세 내역 조회
        OrderItem orderItem = orderItemRepository.findById(1L).get();
        System.out.println(orderItem);
        // 상품 상세 조회
        System.out.println(orderItem.getItem());
        // 주문상세
        System.out.println(orderItem.getOrder());
        // 주문자 상세 조회
        System.out.println(orderItem.getOrder().getMember());
    };

    // U
    @Test
    public void memberAndItemAndmemberupdateTest() {
        // 주문자의 주소 변경
        // Member member = Member.builder()
        // .id(1L)
        // .name("user1")
        // .city("서울")
        // .street("16-3")
        // .zipcode("15350")
        // .build();
        Member member = memberRepository.findById(1L).get();
        member.setStreet("173-12");

        // save: insert or ipdatme
        // 엔티티 매니저가 있어서 현재entity 가 new 인지 기존 entity 인지 구분이 가능함
        // new insert 호출/ 기존 entity : update호출
        // update 는 무조건 전체 컬럼이 수정되는 형태로 작성됨
        System.out.println(memberRepository.save(member));

        // 1번 주문상품의 가격 변경
        // 아이템 수량 가격 변경
        Item item = itemRepository.findById(2L).get();
        item.setPrice(35000);
        itemRepository.save(item);

        OrderItem orderItem = orderItemRepository.findById(1L).get();
        orderItem.setPrice(900);
        orderItemRepository.save(orderItem);
    }

    // D
    @Test
    public void memberAndItemAndOrderDeleteTest() {
        // 주문 상품 취소
        orderItemRepository.deleteById(1L);
        // 주문취소
        orderRepository.deleteById(1L);
    }

    // 양방향
    // Order ==> OrderItem 객체 그래프 탐색
    @Test
    @Transactional
    public void testOrderItemList() {
        Order order = orderRepository.findById(2L).get();
        System.out.println(order);
        // Order==> OrderItem 탐색 시도
        order.getOrderItemList().forEach(orderItem -> System.out.println(orderItem));

    }

    @Test
    @Transactional
    public void testOrderList() {

        Member member = memberRepository.findById(1L).get();
        System.out.println(member);
        // Member==> Order 탐색 시도
        member.getOrders().forEach(order -> System.out.println(order));

    }

    // 일대일
    @Test
    public void testDeliveryInsert() {

        // 배송 정보 입력
        Delivery delivery = Delivery.builder()
                .city("서울시")
                .street("동소문로1가")
                .zipcode("11051")
                .deliveryStatus(DeliveryStatus.READY)
                .build();
        deliveryRepository.save(delivery);

        // order 와 배송정보 연결
        Order order = orderRepository.findById(2L).get();
        order.setDelivery(delivery);
        orderRepository.save(order);
    }

    @Test
    public void testOrderRead() {
        // order 조회
        Order order = orderRepository.findById(2L).get();
        System.out.println(order);
        System.out.println(order.getDelivery());
    }

    @Test
    public void testDeliveryRead() {
        Delivery delivery = deliveryRepository.findById(1L).get();
        System.out.println(delivery);
        System.out.println(delivery.getOrder());
    }
}
