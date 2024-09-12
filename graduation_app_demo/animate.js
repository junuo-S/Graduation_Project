function animate(obj, target, callback)
{
    clearInterval(obj.timer);
    obj.timer = setInterval(function ()
    {
        if(obj.offsetLeft === target)
        {
            clearInterval(obj.timer);
            callback && callback();
        }
        var step = (target - obj.offsetLeft) / 10;
        step = step > 0 ? Math.ceil(step) : Math.floor(step);
        obj.style.left = obj.offsetLeft + step + 'px';
        // console.log(obj.style.left);
    }, 15);
}