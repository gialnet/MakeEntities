import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
public class TicketsController {

private static final Logger logger = LoggerFactory.getLogger(TicketsController.class);


@Autowired
public TicketsController(TicketsRepository ticketsRepository) {
this.ticketsRepository = ticketsRepository;
}
}
