function setOrder(orderId, cusId, cost, orderDate, orderDetails) {
    return {
        orderId: orderId,
        cusId: cusId,
        cost: cost,
        orderDate: orderDate,
        orderDetails: orderDetails,
    };
}
